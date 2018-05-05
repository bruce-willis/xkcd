package combruce_willis.github.xkcd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import combruce_willis.github.xkcd.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements ComicFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
