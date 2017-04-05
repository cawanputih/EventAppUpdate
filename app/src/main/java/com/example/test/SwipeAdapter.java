package com.example.test;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Purba on 4/5/2017.
 */

public class SwipeAdapter extends PagerAdapter {

    int[] gambar = {R.drawable.img0,R.drawable.img1,R.drawable.img2,R.drawable.img3};
    Context mcontext;
    LayoutInflater mlayoutinflater;

    public SwipeAdapter(Context c){
        mcontext = c;
    }

    @Override
    public int getCount() {
        return gambar.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        mlayoutinflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = mlayoutinflater.inflate(R.layout.image_item,container,false);
        ImageView iv = (ImageView) v.findViewById(R.id.image_view);
        iv.setImageResource(gambar[position]);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();
    }

}
