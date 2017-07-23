package com.example.iu.myapplication;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.example.iu.myapplication.base.BaseActivity;
import com.example.iu.myapplication.customize.CustomViewPager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class CollectionActivity extends BaseActivity {

    @Bind(R.id.login_backImage)
    ImageView loginBackImage;
    @Bind(R.id.collection_tab)
    TabLayout collectionTab;
    @Bind(R.id.collection_viewpager)
    CustomViewPager collectionViewpager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    public void initView() {
        ArrayList<Fragment> list = new ArrayList<>();
        String[] str  = {"精彩看点","直播"};
        CollectionAreaFragment areaFragment = new CollectionAreaFragment();
        CollectionLiveFragment liveFragment = new CollectionLiveFragment();

        list.add(areaFragment);
        list.add(liveFragment);

        CollectionFragmentAdapetr collectionFragmentAdapetr = new CollectionFragmentAdapetr(getSupportFragmentManager(), list , str);

        collectionViewpager.setAdapter(collectionFragmentAdapetr);

        collectionTab.setupWithViewPager(collectionViewpager);
        collectionTab.setTabMode(TabLayout.MODE_FIXED);
    }

    @OnClick(R.id.login_backImage)
    public void onViewClicked() {
        finish();
    }
}
