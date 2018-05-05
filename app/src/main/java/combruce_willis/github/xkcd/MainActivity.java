package combruce_willis.github.xkcd;

import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements ComicFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onListFragmentInteraction(View startView, Uri uri) {
        Intent intent = ImageDetailsActivity.getStartIntent(this, uri);
        final String transitionName = getString(R.string.transition_name);
        final ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                this,
                startView,
                transitionName);
        startActivity(intent, options.toBundle());
    }
}
