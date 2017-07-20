package com.example.iu.myapplication.module.chinalive.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.iu.myapplication.model.entity.ChinaBean;

import java.util.ArrayList;

public class LiveChinaTabAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> list;
    private ArrayList<ChinaBean.TablistBean> tablist;
    public LiveChinaTabAdapter(FragmentManager fm,ArrayList<Fragment> list,ArrayList<ChinaBean.TablistBean> tablist) {
        super(fm);
        this.list=list;
        this.tablist=tablist;
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
        return tablist.get(position).getTitle();
    }
}
