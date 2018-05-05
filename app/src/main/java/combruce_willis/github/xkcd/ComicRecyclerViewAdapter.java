package combruce_willis.github.xkcd;

import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
import java.util.Random;

import combruce_willis.github.xkcd.ComicFragment.OnListFragmentInteractionListener;
import combruce_willis.github.xkcd.dummy.DummyContent.DummyItem;


/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ComicRecyclerViewAdapter extends RecyclerView.Adapter<ComicRecyclerViewAdapter.ViewHolder> {

    private static final Random sRandom = new Random();
    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private List<String> ComicsUrl;

    public ComicRecyclerViewAdapter(List<DummyItem> items, List<String> comicsUrl, OnListFragmentInteractionListener listener) {
        mValues = items;
        ComicsUrl = comicsUrl;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_comic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        holder.mItem = mValues.get(position);
//        holder.mIdView.setText(mValues.get(position).id);
//        holder.mContentView.setText(mValues.get(position).content);

        /* How to choose right context for glide
         * @see <a href="https://stackoverflow.com/a/32887693">Stack answer</a>         *
         *
         * Use hardware bitmap
         * @see <a href="https://bumptech.github.io/glide/doc/hardwarebitmaps.html#why-should-we-use-hardware-bitmaps">Documentation</a>
         */
        Glide
                .with((AppCompatActivity) this.mListener)
                .load(ComicsUrl.get(position))
                .apply(new RequestOptions()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .placeholder(new ColorDrawable(sRandom.nextInt())))
                //.transition(withCrossFade())
                .into(holder.comic);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(v, Uri.parse(ComicsUrl.get(position)));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (ComicsUrl == null) return 0;
        return ComicsUrl.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        //public final TextView mIdView;
        //public final TextView mContentView;
        public ImageView comic;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //mIdView = (TextView) view.findViewById(R.id.item_number);
            //mContentView = (TextView) view.findViewById(R.id.content);
            comic = (ImageView) view.findViewById(R.id.imageView);
        }

//        @Override
//        public String toString() {
//            return super.toString() + " '" + mContentView.getText() + "'";
//        }
    }
}
