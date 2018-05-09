package combruce_willis.github.xkcd.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import combruce_willis.github.xkcd.Fragment.FullScreenComicFragment;
import combruce_willis.github.xkcd.Model.ComicsData;

public class ComicPagerAdapter extends FragmentStatePagerAdapter {
    public ComicPagerAdapter(Fragment fragment) {
        // Note: Initialize with the child fragment manager.
        super(fragment.getChildFragmentManager());
    }

    @Override
    public int getCount() {
        return ComicsData.COMICS.length;
    }

    @Override
    public Fragment getItem(int position) {
        return FullScreenComicFragment.newInstance(ComicsData.COMICS[position]);
    }
}
