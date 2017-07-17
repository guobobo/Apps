package com.example.iu.myapplication.module.home.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/12.
 * 轮播图的Adapter
 */

public class Home_page_Rotation extends PagerAdapter{
    ArrayList<View> rotation_array;
    public Home_page_Rotation(ArrayList<View> rotation_array) {
        this.rotation_array = rotation_array;
    }

    @Override
    public int getCount() {
        return  Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        int newPosition = position % rotation_array.size();
        container.addView(rotation_array.get(newPosition));



        return rotation_array.get(newPosition);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);

    }
}
