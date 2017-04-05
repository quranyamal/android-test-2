package com.suitschool.screeningtest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by amal on 3/31/2017.
 */

public class EventAdapter extends ArrayAdapter<Event> {
    Context context;

    public EventAdapter(Context context, List<Event> items) {
        super(context, 0, items);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Event event = getItem(position);
        TextView txtJudul;
        TextView txtTanggal;
        ImageView imageView;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_event, null);
        }

        txtJudul = (TextView) convertView.findViewById(R.id.nama_acara);
        txtTanggal = (TextView) convertView.findViewById(R.id.tanggal_acara);
        imageView = (ImageView) convertView.findViewById(R.id.gambar_acara);

        txtTanggal.setText(event.getTanggal());
        imageView.setImageResource(R.drawable.tugu_solok);
        txtJudul.setText(event.getJudul());

        return convertView;
    }

}
