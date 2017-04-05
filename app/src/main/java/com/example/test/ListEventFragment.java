package com.example.test;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import static android.R.id.list;
import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListEventFragment extends Fragment {

    ListView list;

    public ListEventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_list_event, container, false);
        list = (ListView) v.findViewById(R.id.eventList);
        list.setAdapter(new List_Adapter(getActivity()));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event ee= (Event) parent.getItemAtPosition(position);
                String namaevent = ee.getName();
                Intent i = new Intent();
                i.putExtra("key_event",namaevent);
                getActivity().setResult(RESULT_OK,i);
                getActivity().finish();
            }
        });

        return v;

    }


}

