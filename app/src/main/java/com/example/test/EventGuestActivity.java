package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class EventGuestActivity extends Activity {

    Button btnevent;
    Button btnguest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_event_guest);

        String nama = getIntent().getStringExtra("key_nama");

        TextView txtNama = (TextView) findViewById(R.id.nama);
        txtNama.setText("Nama : "+nama);

        btnevent = (Button) findViewById(R.id.btnevent);
        btnguest = (Button) findViewById(R.id.btnguest);

        btnevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EventGuestActivity.this,NewActivity.class);
                startActivityForResult(i,1);
            }
        });

        btnguest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EventGuestActivity.this,GuestActivity.class);
                startActivityForResult(i,2);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                String eventname= data.getStringExtra("key_event");
                btnevent.setText(eventname);
            }
        }else if(requestCode == 2){
            if(resultCode == RESULT_OK){
                String guestname = data.getStringExtra("key_guest");
                btnguest.setText(guestname);
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }
}
