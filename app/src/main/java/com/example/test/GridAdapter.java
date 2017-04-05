package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Purba on 3/31/2017.
 */

public class GridAdapter extends BaseAdapter{

    Context c;
    ArrayList<Guest> gl;

    public GridAdapter(Context c, ArrayList<Guest> gl) {
        this.c = c;
        this.gl = gl;
    }

    @Override
    public int getCount() {
        return gl.size();
    }

    @Override
    public Object getItem(int position) {
        return gl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView= LayoutInflater.from(c).inflate(R.layout.grid_item,parent,false);
        }


        TextView nameText = (TextView) convertView.findViewById(R.id.name);

        Guest guest = (Guest) this.getItem(position);

        final String id = guest.getId();
        final String name = guest.getName();
        final String birthdate = guest.getBirthdate();

        nameText.setText(name);
        return convertView;
    }
}
