package com.suitschool.screeningtest;

/**
 * Created by amal on 3/31/2017.
 */

class Event {
    private String judul;
    private String tanggal;
    private String image;
    private double latitude;
    private double longitude;

    Event(String title, String tanggal, String image) {
        this.judul = title;
        this.tanggal = tanggal;
        this.image = image;
        this.latitude = 0;
        this.longitude = 0;
    }

    Event(String title, String tanggal, String image,double lat, double lon) {
        this.judul = title;
        this.tanggal = tanggal;
        this.image = image;
        this.latitude = lat;
        this.longitude = lon;
    }

    String getJudul() {
        return judul;
    }

    String getTanggal() {
        return tanggal;
    }

    String getImage() {
        return image;
    }

    double getLatitude() {
        return latitude;
    }

    double getLongitude() {
        return longitude;
    }
}
