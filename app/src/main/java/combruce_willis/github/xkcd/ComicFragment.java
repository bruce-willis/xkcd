package combruce_willis.github.xkcd;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import combruce_willis.github.xkcd.dummy.DummyContent;
import combruce_willis.github.xkcd.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ComicFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private OnListFragmentInteractionListener mListener;

    private ArrayList<String> ComicsUrl = new ArrayList<>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ComicFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ComicFragment newInstance(int columnCount) {
        ComicFragment fragment = new ComicFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ComicsUrl.add("https://imgs.xkcd.com/comics/copyright.jpg");
        ComicsUrl.add("https://imgs.xkcd.com/comics/snapple.jpg");
        ComicsUrl.add("https://imgs.xkcd.com/comics/island_color.jpg");
        ComicsUrl.add("https://imgs.xkcd.com/comics/monty_python.jpg");
        ComicsUrl.add("https://imgs.xkcd.com/comics/just_alerting_you.jpg");
        ComicsUrl.add("https://imgs.xkcd.com/comics/canyon_small.jpg");
        ComicsUrl.add("https://imgs.xkcd.com/comics/barrel_cropped_(1).jpg");
        ComicsUrl.add("https://imgs.xkcd.com/comics/ferret.jpg");
        ComicsUrl.add("https://imgs.xkcd.com/comics/irony_color.jpg");
        ComicsUrl.add("https://imgs.xkcd.com/comics/blownapart_color.jpg");
        ComicsUrl.add("https://imgs.xkcd.com/comics/what_if.jpg");
        ComicsUrl.add("https://imgs.xkcd.com/comics/tree_cropped_(1).jpg");
        ComicsUrl.add("https://imgs.xkcd.com/comics/george_clinton.jpg");
        ComicsUrl.add("https://imgs.xkcd.com/comics/firefly.jpg");
        ComicsUrl.add("https://imgs.xkcd.com/comics/girl_sleeping_noline_(1).jpg");
        ComicsUrl.add("https://imgs.xkcd.com/comics/poisson.jpg");
        ComicsUrl.add("https://imgs.xkcd.com/comics/barrel_mommies.jpg");
        ComicsUrl.add("https://imgs.xkcd.com/comics/pi.jpg");
        ComicsUrl.add("https://imgs.xkcd.com/comics/landscape_cropped_(1).jpg");
        ComicsUrl.add("https://imgs.xkcd.com/comics/red_spiders_small.jpg");

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comic_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new ComicRecyclerViewAdapter(DummyContent.ITEMS, ComicsUrl, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(View startView, Uri uri);
    }
}
