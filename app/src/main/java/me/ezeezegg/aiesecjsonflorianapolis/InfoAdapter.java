package me.ezeezegg.aiesecjsonflorianapolis;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.InputStream;
import java.util.List;

import me.ezeezegg.aiesecjsonflorianapolis.models.info;

/**
 * Created by egarcia on 9/19/15.
 */
public class InfoAdapter extends BaseAdapter {
    private MainActivity activity;
    private LayoutInflater inflater;
    private List<info> infoItems;
    //ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public InfoAdapter(MainActivity activity, List<info> infoItems) {
        this.activity = activity;
        this.infoItems = infoItems;
    }

    @Override
    public int getCount() {
        return infoItems.size();
    }

    @Override
    public Object getItem(int location) {
        return infoItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_info, null);

        /*if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);*/
        ImageView image = (ImageView) convertView.findViewById(R.id.ivImage);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView authors = (TextView) convertView.findViewById(R.id.authors);
        //ImageView image =  convertView.findViewById(R.id.ivImage);

        // getting movie data for the row
        info m = infoItems.get(position);

        // thumbnail image
        //thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        // title
        title.setText(String.valueOf(m.getTitle()));

        // date
        date.setText("Fecha: " + String.valueOf(m.getDate()));
        authors.setText("authors: " + String.valueOf(m.getAuthors()));
        new DownloadImageTask(image).execute(String.valueOf(m.getImage()));
        Log.d("pinta", String.valueOf(m.getDate()));
        Log.d("pinta", String.valueOf(m.getTitle()));
        Log.d("pinta", String.valueOf(m.getAuthors()));

        return convertView;
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
