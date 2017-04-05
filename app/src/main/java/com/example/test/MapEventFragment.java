package com.example.test;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapEventFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap mgooglemap;
    MapView mmapview;
    View mview;
    ViewPager vp;
    SwipeAdapter madapter;
    LatLng[] LL = {new LatLng(-6.893440, 107.618528),new LatLng(-6.896827, 107.616146),new LatLng(-6.889851, 107.613786),new LatLng(-6.898334, 107.618013)};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mview = inflater.inflate(R.layout.fragment_map_event,container,false);
        vp = (ViewPager) mview.findViewById(R.id.swipeimage);
        madapter = new SwipeAdapter(getActivity());
        vp.setAdapter(madapter);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                mgooglemap.addMarker(new MarkerOptions().position(LL[position]));
                mgooglemap.moveCamera(CameraUpdateFactory.newLatLngZoom(LL[position],(float)18));

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        return mview;

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mmapview = (MapView) mview.findViewById(R.id.map);
        if(mmapview  != null){
            mmapview.onCreate(null);
            mmapview.onResume();
            mmapview.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mgooglemap = googleMap;

        LatLng monju = new LatLng(-6.893440,107.618528);
        mgooglemap.addMarker(new MarkerOptions().position(monju).title("Marker in Monju"));
        mgooglemap.moveCamera(CameraUpdateFactory.newLatLngZoom(monju,(float)17));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mview!=null){
            ViewGroup parentview = (ViewGroup) mview.getParent();
            if(parentview!=null){
                parentview.removeAllViews();
            }
        }
    }



}


