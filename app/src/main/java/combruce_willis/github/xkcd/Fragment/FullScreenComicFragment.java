package combruce_willis.github.xkcd.Fragment;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.PhotoView;

import combruce_willis.github.xkcd.Model.Comic;
import combruce_willis.github.xkcd.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FullScreenComicFragment extends Fragment {

    private static final String KEY_IMAGE_RES = "KEY_IMAGE_RES";


    public static FullScreenComicFragment newInstance(Comic comic) {
        FullScreenComicFragment fragment = new FullScreenComicFragment();
        Bundle argument = new Bundle();
        argument.putParcelable(KEY_IMAGE_RES, comic);
        fragment.setArguments(argument);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_full_screen_comic, container, false);

        Bundle arguments = getArguments();
        Comic comic = arguments.getParcelable(KEY_IMAGE_RES);
        String imageRes = comic.getImageUrl();

        //getActivity().setTitle(comic.getTitle());
        //((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(comic.getTitle());

        // Just like we do when binding views at the grid, we set the transition name to be the string
        // value of the image res.
        view.findViewById(R.id.full_screen_comic).setTransitionName(String.valueOf(imageRes));

        // Load the image with Glide to prevent OOM error when the image drawables are very large.
        Glide.with(this)
                .load(imageRes)
                .apply(new RequestOptions()
                        .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .dontTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable>
                            target, boolean isFirstResource) {
                        // The postponeEnterTransition is called on the parent ImagePagerFragment, so the
                        // startPostponedEnterTransition() should also be called on it to get the transition
                        // going in case of a failure.
                        getParentFragment().startPostponedEnterTransition();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable>
                            target, DataSource dataSource, boolean isFirstResource) {
                        // The postponeEnterTransition is called on the parent ImagePagerFragment, so the
                        // startPostponedEnterTransition() should also be called on it to get the transition
                        // going when the image is ready.
                        getParentFragment().startPostponedEnterTransition();
                        return false;
                    }
                })
                .into((PhotoView) view.findViewById(R.id.full_screen_comic));
        return view;
    }

}
