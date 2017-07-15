package com.example.iu.myapplication.module.pandalive.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class LivePagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private List<String> tabList=new ArrayList<>();
    public LivePagerAdapter(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
        tabList.add("直播");
        tabList.add("精彩一刻");
        tabList.add("当熊不让");
        tabList.add("超萌滚滚秀");
        tabList.add("熊猫档案");
        tabList.add("熊猫TOP榜");
        tabList.add("熊猫那些事儿");
        tabList.add("特别节目");
        tabList.add("原创新闻");
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabList.get(position);
    }
}
