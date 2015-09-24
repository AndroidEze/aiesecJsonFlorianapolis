package me.ezeezegg.aiesecjsonflorianapolis.intents;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

import me.ezeezegg.aiesecjsonflorianapolis.R;

/**
 * Created by egarcia on 9/19/15.
 */
public class infoIntent extends Activity {
    String passedTitle = null;
    String passedDate = null;
    String passedRating = null;
    String imageDetails = null;
    private ImageView imageView = null;
    private TextView putTitle = null;
    private TextView putDate = null;
    private TextView putRating = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_intent);
        passedTitle = getIntent().getExtras().getString("title");
        passedDate = getIntent().getExtras().getString("date");
        passedRating = getIntent().getExtras().getString("rating");
        imageDetails = getIntent().getExtras().getString("image");
        putTitle = (TextView) findViewById(R.id.titleIntent);
        putDate = (TextView) findViewById(R.id.dateIntent);
        putRating = (TextView) findViewById(R.id.ratingIntent);
        imageView = (ImageView) findViewById(R.id.imageDetails);
        putTitle.setText("Movie: "+ passedTitle);
        putDate.setText("Year Premiere: "+ passedDate);
        putRating.setText("Rating: "+ passedRating);


        new DownloadImageTask(imageView).execute(imageDetails);

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }

    }
}
