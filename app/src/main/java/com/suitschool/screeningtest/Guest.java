package com.suitschool.screeningtest;

import android.util.Log;

/**
 * Created by amal on 3/31/2017.
 */

public class Guest {
    private int id;
    private String nama;
    private String ttl;
    private int gambar;

    public Guest(int id, String name, String birthdate, int image) {
        this.id = id;
        this.nama = name;
        this.ttl = birthdate;
        this.gambar = image;
        Log.d("GUEST", "Guest constructor called");
    }

    int getId() { return id; }
    String getNama() { return nama; }
    String getTtl() { return ttl; }
    int getGambar() { return gambar; }
}
