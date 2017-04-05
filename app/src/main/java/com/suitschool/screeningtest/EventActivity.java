package com.suitschool.screeningtest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {
    ArrayList<Event> events;
    private ViewSwitcher mViewSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        events = new ArrayList<>();
        createDummyEvents();

        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.event_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("");
        }

        ColorDrawable dividerColor = new ColorDrawable(0xaaa);
        ListView listView = (ListView) findViewById(R.id.event_view);
        listView.setDivider(dividerColor);
        listView.setDividerHeight(15);

        final EventAdapter adapter = new EventAdapter(this, events);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(EventActivity.this, MenuActivity.class);
                String judulAcara = adapter.getItem(position).getJudul();
                intent.putExtra("ACARA", judulAcara);
                setResult(MenuActivity.ACARA_DIPILIH, intent);
                finish();
            }
        });

        mViewSwitcher = (ViewSwitcher) findViewById(R.id.event_switcher_view);
        //private GoogleMap googleMap = ((MapView) findViewById(R.id.map)).getMap();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        int itemId = item.getItemId();
        switch(itemId) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_back:
                finish();
                break;
            case R.id.action_search:
                Toast.makeText(EventActivity.this, "search request", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_new:
                mViewSwitcher.showNext();
                //getMenuInflater().inflate(R.menu.event, menu);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void createDummyEvents() {
        Event event1 = new Event("Internship di Suitmedia", "1 Juni 2017", "suitmedia.jpg", -6.886, 107.609);
        Event event2 = new Event("Pulang kampung", "25 Juni 2017", "pantaipadang.jpg", -6.886112, 107.609415);
        Event event3 = new Event("Cari jodoh", "7 Juli 2017", "jodoh.jpg", -6.894281, 107.611134);
        Event event4 = new Event("Traveling", "20 Agus 2017", "traveling.jpg", -6.882352, 107.613166);
        Event event5 = new Event("Ngambizz...", "25 Agus 2017", "ngambis.jpg", -6.893439, 107.605594);
        Event event6 = new Event("Laloe", "25 Agus 2017", "ngambis.jpg", -6.885018, 107.613559);
        events.add(event1);
        events.add(event2);
        events.add(event3);
        events.add(event4);
        events.add(event5);
        events.add(event6);
    }
}
