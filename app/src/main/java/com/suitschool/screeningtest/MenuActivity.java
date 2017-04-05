package com.suitschool.screeningtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    static final int ACARA_DIPILIH = 1;
    static final int GUEST_DIPILIH = 2;

    TextView namaTextView;
    Button eventButton, guestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        namaTextView = (TextView) findViewById(R.id.menu_nama_textview);
        namaTextView.setText("Nama                      : " + getIntent().getStringExtra("NAMA"));
        eventButton = (Button) findViewById(R.id.menu_event_button);
        guestButton = (Button) findViewById(R.id.menu_guest_button);

        Log.d("CREATE", "onCreate MenuActivity");
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("req res", "req="+requestCode+" res="+resultCode);
        switch (requestCode) {
            case ACARA_DIPILIH:
                if (resultCode!=0)
                    eventButton.setText(data.getStringExtra("ACARA"));
                break;
            case GUEST_DIPILIH:
                if (resultCode!=0) {
                    Bundle extras = data.getExtras();
                    guestButton.setText(extras.getString("NAMA_TAMU"));
                    String ttl = extras.getString("TTL_TAMU");
                    Log.d("NAMA TTL", "NAMA="+data.getStringExtra("NAMA")+" ttl="+ttl);
                    int phoneCode = Integer.parseInt(ttl.substring(8,10));
                    String phoneType;

                    if (phoneCode%2 == 0) {
                        if (phoneCode%3 == 0) phoneType = "iOS";
                        else phoneType = "blackberry";
                    } else {
                        if (phoneCode%3 == 0) phoneType = "android";
                        else phoneType = "feature phone";
                    }
                    Toast.makeText(MenuActivity.this, "" + phoneType, Toast.LENGTH_SHORT).show();
                }
                break;
        };
    }

    public void onClickEvent(View view) {
        Intent intent = new Intent(this, EventActivity.class);
        startActivityForResult(intent, ACARA_DIPILIH);
    }

    public void onClickGuest(View view) {
        Intent intent = new Intent(this, GuestActivity.class);
        startActivityForResult(intent, GUEST_DIPILIH);
    }
}
