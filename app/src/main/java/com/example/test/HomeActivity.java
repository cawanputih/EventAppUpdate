package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.color.white;

public class HomeActivity extends Activity {

    TextView txtname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);


        Button btnnext = (Button) findViewById(R.id.buttonnext);
        txtname = (TextView) findViewById(R.id.nama);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String namauser = txtname.getText().toString();

                Intent i = new Intent(HomeActivity.this,EventGuestActivity.class);
                i.putExtra("key_nama",namauser);
                if(ispalindrom(namauser)){
                    Toast.makeText(HomeActivity.this,"is Palindrome",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(HomeActivity.this,"not Palindrome",Toast.LENGTH_LONG).show();
                }
                startActivity(i);
            }
        });

    }

    public boolean ispalindrom(String xx){
        int i = 0 ;
        int j = xx.length()-1;
        char[] charxx = xx.toCharArray();

        while (i<=j){
            if(charxx[i] == ' '){
                i++;
            }else if(charxx[j] == ' '){
                j--;
            }else{
                if(charxx[i] == charxx[j]){
                    i++;
                    j--;
                }else{
                    return false;
                }
            }
        }

        return true;
    }
}