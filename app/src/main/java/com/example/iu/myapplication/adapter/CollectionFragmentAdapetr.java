package com.example.iu.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dell on 2017/7/18.
 */

public class CollectionFragmentAdapetr extends FragmentPagerAdapter {

    private ArrayList<Fragment> list;
    private String[] str;
    public CollectionFragmentAdapetr(FragmentManager fm,ArrayList<Fragment> list ,String[] str) {
        super(fm);
        this.list = list;
        this.str = str;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return str[position];
    }
}
