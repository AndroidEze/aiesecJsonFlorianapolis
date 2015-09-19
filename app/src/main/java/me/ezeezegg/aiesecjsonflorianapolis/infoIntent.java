package me.ezeezegg.aiesecjsonflorianapolis;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

/**
 * Created by egarcia on 9/19/15.
 */
public class infoIntent extends Activity {
    String passedVar = null;
    String imageDetails = null;
    private ImageView imageView = null;
    private TextView passedView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_intent);
        passedVar = getIntent().getExtras().getString("description");
        imageDetails = getIntent().getExtras().getString("image");
        passedView = (TextView) findViewById(R.id.passed);
        imageView = (ImageView) findViewById(R.id.imageDetails);
        passedView.setText("description: "+ passedVar);
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
