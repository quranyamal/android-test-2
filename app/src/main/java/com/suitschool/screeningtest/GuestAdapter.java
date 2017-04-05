package com.suitschool.screeningtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by amal on 3/31/2017.
 */

public class GuestAdapter extends ArrayAdapter<Guest>{

    public GuestAdapter(Context context, ArrayList<Guest> guests) {
        super(context, 0, guests);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        ImageView imageView;
        Guest guest = getItem(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_guest, parent, false);
        }

        textView = (TextView) convertView.findViewById(R.id.guest_text_item);
        imageView = (ImageView) convertView.findViewById(R.id.guest_image_item);

        textView.setText(guest.getNama());
        imageView.setImageResource(R.drawable.suitlogo);
        return convertView;
    }
}
