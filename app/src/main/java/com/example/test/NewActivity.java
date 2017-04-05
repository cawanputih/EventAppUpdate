package com.example.test;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Purba on 4/5/2017.
 */

public class NewActivity extends Activity{

    boolean status = false;
    Button bn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ListEventFragment fr= new ListEventFragment();
        ft.replace(R.id.fragment_container,fr);
        ft.commit();
        bn = (Button) findViewById(R.id.btnswap);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                if (!status){
                    MapEventFragment f1= new MapEventFragment();
                    ft.replace(R.id.fragment_container,f1);
                    ft.commit();
                    status = true;
                }else{
                    ListEventFragment f2= new ListEventFragment();
                    ft.replace(R.id.fragment_container,f2);
                    ft.commit();
                    status = false;
                }
            }
        });
    }
}