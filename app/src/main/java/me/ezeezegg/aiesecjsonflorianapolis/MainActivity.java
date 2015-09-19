package me.ezeezegg.aiesecjsonflorianapolis;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.ezeezegg.aiesecjsonflorianapolis.models.info;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    // json object response url
    private String url = "http://api.androidhive.info/json/movies.json";
    List<info> infoList = new ArrayList<info>();
    ListView listView;
    InfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView   = (ListView) findViewById(R.id.list);
        adapter = new InfoAdapter(this, infoList);
        //
        // infoList = new ArrayList<info>();
        // Creating volley request obj
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long id) {
                // TODO Auto-generated method stub
                Intent i = new Intent(MainActivity.this, infoIntent.class);
                i.putExtra("description", String.valueOf(infoList.get(position).getTitle()));
                i.putExtra("image", String.valueOf(infoList.get(position).getImage()));
                startActivity(i);
                //Toast.makeText(getApplicationContext(), actorsList.get(position).getDescription(), Toast.LENGTH_LONG).show();
            }
        });

        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        //hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                info Info = new info();
                                Info.setTitle(obj.getString("title"));
                                Info.setDate(obj.getString("releaseYear"));
                                Info.setImage(obj.getString("image"));
                                Log.d("title", obj.getString("title"));
                                Log.d("date", obj.getString("releaseYear"));
                                //Info.setRating(((Number) obj.get("rating").doubleValue());
                                //Info.setDate(obj.getInt("releaseYear"));

                                infoList.add(Info);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                //hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);
        //makeJsonObjectRequest();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Method to make json object request where json response starts wtih {
     * */

}
