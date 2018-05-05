package combruce_willis.github.xkcd;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageDetailsActivity extends AppCompatActivity {

    public static Intent getStartIntent(Context context, Uri imageUri) {
        Intent intent = new Intent(context, ImageDetailsActivity.class);
        intent.setData(imageUri);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);

        ImageView comic = (ImageView) findViewById(R.id.fullScreenComic);

        Glide
                .with(this)
                .load(getIntent().getData())
                .into(comic);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
