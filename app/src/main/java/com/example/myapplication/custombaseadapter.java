package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class custombaseadapter extends BaseAdapter {
    Context context;
    String Listinstitution[];
    int Listimage[];
    LayoutInflater inflater;

    public custombaseadapter(Context ctx, String[] listint, int[] images) {
        this.context = ctx;
        this.Listinstitution = listint;
        this.Listimage = images;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return Listinstitution.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_item_layout, parent, false);
        }

        TextView txtView = convertView.findViewById(R.id.imageid);
        ImageView imageView = convertView.findViewById(R.id.imageicon);

        txtView.setText(Listinstitution[position]);
        imageView.setImageResource(Listimage[position]);

        return convertView;
}
}