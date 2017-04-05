package com.suitschool.screeningtest;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLConnection;
import java.util.ArrayList;

public class GuestActivity extends AppCompatActivity {
    private ArrayList<Guest> guests;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

        guests = new ArrayList<>();
        new JSONParser().execute();
        Log.d("GUEST", "jumlah geust: " + guests.size());

        Toolbar toolbar = (Toolbar) findViewById(R.id.guest_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar()!=null) {
            getSupportActionBar().setTitle("");
        }

        gridView = (GridView) findViewById(R.id.grid_view);
    }

    boolean isPrimeMonth(int month) {
        return (month==2||month==3||month==5||month==7||month==11);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.guest, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        int itemId = item.getItemId();
        switch(itemId) {
            case R.id.action_back:
                finish();
                break;
            case R.id.action_sync:
                Toast.makeText(GuestActivity.this, "syncing guest data", Toast.LENGTH_SHORT).show();
                Toast.makeText(GuestActivity.this, "data is up to date", Toast.LENGTH_SHORT).show();
                new JSONParser().execute();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class JSONParser extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            HttpHandler httpHandler = new HttpHandler();
            String urlStr = "http://dry-sierra-6832.herokuapp.com/api/people";
            String jsonStr = httpHandler.makeServiceCall(urlStr);

            Log.d("JSON", "JSON: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONArray guestJson = new JSONArray(jsonStr);
                    int numObject = guestJson.length();
                    for (int i = 0; i < numObject; i++) {
                        JSONObject jObj = guestJson.getJSONObject(i);
                        if (i>=guests.size()){
                            int id = jObj.getInt("id");
                            String nama = jObj.getString("name");
                            String ttl = jObj.getString("birthdate");
                            Guest guest = new Guest(id, nama, ttl, R.drawable.suitlogo);
                            guests.add(guest);
                            Log.d("JSON", "GUEST ADDED..................." + nama);
                        }
                    }
                } catch (JSONException jex) {
                    Log.e("JSON ERROR", "JSON EXXXXXXXCCEPTION");
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void response) {
            super.onPostExecute(response);
            if (response != null) {
                try {
                    Log.e("App", "Success: ");
                } catch (Exception ex) {
                    Log.e("App", "Failure", ex);
                }
            }

            final GuestAdapter adapter = new GuestAdapter(GuestActivity.this, guests);
            gridView.setAdapter(adapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    //Toast.makeText(GuestActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                    String namaTamu = adapter.getItem(position).getNama();
                    String ttlTamu = adapter.getItem(position).getTtl();

                    Bundle extras = new Bundle();
                    extras.putString("NAMA_TAMU", namaTamu);
                    extras.putString("TTL_TAMU", ttlTamu);

                    Intent intent = new Intent(GuestActivity.this, MenuActivity.class);
                    intent.putExtras(extras);
                    setResult(MenuActivity.GUEST_DIPILIH, intent);
                    finish();
                }
            });
        }
    }
}