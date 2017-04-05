package com.example.test;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class GuestActivity extends AppCompatActivity {

    private String TAG = GuestActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private GridView gv;

    private static String url = "http://dry-sierra-6832.herokuapp.com/api/people/";

    ArrayList<Guest> guestList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

        guestList = new ArrayList<>();
        gv = (GridView) findViewById(R.id.gridGuest);

        new GetGuest().execute();

        final SwipeRefreshLayout swipe = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe.setRefreshing(false);
                        new GetGuest().execute();
                    }
                },1000);
            }
        });
    }

    private class GetGuest extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(GuestActivity.this);
            pDialog.setMessage("Loading ...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            String jsonStr = sh.makeServiceCall(url);


            if (jsonStr != null) {
                try {
                    JSONArray jguest = new JSONArray(jsonStr);

                    guestList.clear();
                    Guest guest;

                    for (int i = 0; i < jguest.length(); i++) {
                        JSONObject c = jguest.getJSONObject(i);

                        String id = c.getString("id");
                        String name = c.getString("name");
                        String birthdate= c.getString("birthdate");

                        guest  = new Guest();

                        guest.setId(id);
                        guest.setName(name);
                        guest.setBirthdate(birthdate);

                        guestList.add(guest);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        public boolean isprima(int n){

                if(n<=1){
                    return false;
                }else {

                    int faktor = 0;

                    for (int i = 1; i <= n; i++) {
                        if (n % i == 0) {
                            faktor++;
                        }
                    }

                    if (faktor>2) {
                        return false;
                    }else{
                        return true;
                    }
                }

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();


            gv.setAdapter(new GridAdapter(GuestActivity.this,guestList));

            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Guest gg= (Guest) parent.getItemAtPosition(position);
                    String namaguest = gg.getName();
                    String tanggallahir = gg.getBirthdate();
                    String parts[] = tanggallahir.split("-");
                    String bulankelahiran = parts[1];
                    int foo = Integer.parseInt(bulankelahiran);
                    String pesan = "";
                    if (isprima(foo)){
                        pesan = "is Prima";
                    }else {
                        pesan = "not Prima";
                    }
                    Toast.makeText(GuestActivity.this,pesan,Toast.LENGTH_LONG).show();
                    Intent i = new Intent();
                    i.putExtra("key_guest",namaguest);
                    setResult(RESULT_OK,i);
                    finish();
                }
            });
        }
    }
}
