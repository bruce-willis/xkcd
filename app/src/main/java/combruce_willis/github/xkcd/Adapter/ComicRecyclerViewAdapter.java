package combruce_willis.github.xkcd.Adapter;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import combruce_willis.github.xkcd.Fragment.ComicPagerFragment;
import combruce_willis.github.xkcd.MainActivity;
import combruce_willis.github.xkcd.Model.ComicsData;
import combruce_willis.github.xkcd.R;


public class ComicRecyclerViewAdapter extends RecyclerView.Adapter<ComicRecyclerViewAdapter.ComicViewHolder> {
    private final RequestManager requestManager;
    private final IViewHolderListener IViewHolderListener;

    public ComicRecyclerViewAdapter(Fragment fragment) {
        this.requestManager = Glide.with(fragment);
        this.IViewHolderListener = new ViewHolderListener(fragment);
    }


    @NonNull
    @Override
    public ComicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_comic_item, parent, false);
        return new ComicViewHolder(view, requestManager, IViewHolderListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ComicViewHolder holder, final int position) {
        holder.onBind();
    }

    @Override
    public int getItemCount() {
        if (ComicsData.COMICS == null) return 0;
        return ComicsData.COMICS.length;
    }

    /**
     * A listener that is attached to all ViewHolders to handle image loading events and clicks.
     */
    private interface IViewHolderListener {

        void onLoadCompleted(ImageView view, int adapterPosition);

        void onItemClicked(View view, int adapterPosition);
    }

    /**
     * Default {@link IViewHolderListener} implementation.
     */
    private static class ViewHolderListener implements IViewHolderListener {

        private Fragment fragment;
        private AtomicBoolean enterTransitionStarted;

        ViewHolderListener(Fragment fragment) {
            this.fragment = fragment;
            this.enterTransitionStarted = new AtomicBoolean();
        }

        @Override
        public void onLoadCompleted(ImageView view, int position) {
            // Call startPostponedEnterTransition only when the 'selected' image loading is completed.
            if (MainActivity.currentPosition != position) {
                return;
            }
            if (enterTransitionStarted.getAndSet(true)) {
                return;
            }
            fragment.startPostponedEnterTransition();
        }

        /**
         * Handles a view click by setting the current position to the given {@code position} and
         * starting a {@link  ComicPagerFragment} which displays the image at the position.
         *
         * @param view     the clicked {@link ImageView} (the shared element view will be re-mapped at the
         *                 GridFragment's SharedElementCallback)
         * @param position the selected view position
         */
        @Override
        public void onItemClicked(View view, int position) {
            // Update the position.
            MainActivity.currentPosition = position;

            // Exclude the clicked card from the exit transition (e.g. the card will disappear immediately
            // instead of fading out with the rest to prevent an overlapping animation of fade and move).
            ((TransitionSet) fragment.getExitTransition()).excludeTarget(view, true);

            ImageView transitioningView = view.findViewById(R.id.card_comic);
            fragment.getFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true) // Optimize for shared element transition
                    .addSharedElement(transitioningView, transitioningView.getTransitionName())
                    .replace(R.id.fragment_container, new ComicPagerFragment(), ComicPagerFragment.class
                            .getSimpleName())
                    .addToBackStack(null)
                    .commit();
        }
    }

    static class ComicViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        private static final Random sRandom = new Random();
        private final ImageView image;
        private final RequestManager requestManager;
        private final IViewHolderListener IViewHolderListener;

        ComicViewHolder(View itemView, RequestManager requestManager,
                        IViewHolderListener IViewHolderListener) {
            super(itemView);
            this.image = itemView.findViewById(R.id.card_comic);
            this.requestManager = requestManager;
            this.IViewHolderListener = IViewHolderListener;
            itemView.findViewById(R.id.card_view).setOnClickListener(this);
        }

        /**
         * Binds this view holder to the given adapter position.
         * <p>
         * The binding will load the image into the image view, as well as set its transition name for
         * later.
         */
        void onBind() {
            int adapterPosition = getAdapterPosition();
            setImage(adapterPosition);
            // Set the string value of the image resource as the unique transition name for the view.
            image.setTransitionName(String.valueOf(ComicsData.COMICS[adapterPosition].getImageUrl()));
        }

        void setImage(final int adapterPosition) {
            // Load the image with Glide to prevent OOM error when the image drawables are very large.
            requestManager
                    .load(ComicsData.COMICS[adapterPosition].getImageUrl())
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .format(DecodeFormat.PREFER_ARGB_8888)
                            .placeholder(new ColorDrawable(sRandom.nextInt())))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                    Target<Drawable> target, boolean isFirstResource) {
                            IViewHolderListener.onLoadCompleted(image, adapterPosition);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable>
                                target, DataSource dataSource, boolean isFirstResource) {
                            IViewHolderListener.onLoadCompleted(image, adapterPosition);
                            return false;
                        }
                    })
                    .into(image);
        }

        @Override
        public void onClick(View v) {
            // Let the listener start the ImagePagerFragment.
            IViewHolderListener.onItemClicked(v, getAdapterPosition());
        }
    }
}
