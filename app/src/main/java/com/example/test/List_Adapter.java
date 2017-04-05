package com.example.test;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Purba on 3/31/2017.
 */

class List_Adapter extends BaseAdapter {

    ArrayList<Event> arr;
    Context context;

    public List_Adapter(Context c) {
        context = c;
        arr = new ArrayList<Event>();
        Resources res = c.getResources();
        String[] names = res.getStringArray(R.array.names);
        String[] dates= res.getStringArray(R.array.dates);
        int[] images = {R.drawable.img0,R.drawable.img1,R.drawable.img2,R.drawable.img3};

        for (int i = 0 ; i < 4 ;i++) {
            arr.add(new Event(names[i], dates[i], images[i]));
        }

    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        }

        TextView namaevent = (TextView) convertView.findViewById(R.id.namaevent);
        TextView tanggalevent = (TextView) convertView.findViewById(R.id.tanggalevent);
        ImageView image = (ImageView) convertView.findViewById(R.id.gambar);

        Event temp = arr.get(position);

        namaevent.setText(temp.name);
        tanggalevent.setText(temp.date);
        image.setImageResource(temp.image);
        return convertView;
    }

}