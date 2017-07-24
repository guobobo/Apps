package com.example.iu.myapplication;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.iu.myapplication.base.BaseActivity;
import com.example.iu.myapplication.customize.CustomViewPager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CollectionActivity extends BaseActivity {

    @Bind(R.id.collection_tab)
    TabLayout collectionTab;
    @Bind(R.id.collection_viewpager)
    CustomViewPager collectionViewpager;
    @Bind(R.id.activity_collection)
    LinearLayout activityCollection;
    @Bind(R.id.login_backImage)
    ImageView loginBackImage;
    @Bind(R.id.collection_edit)
    TextView collectionEdit;
    private CollectionAreaFragment areaFragment;
    private CollectionLiveFragment liveFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    public void initView() {
        ArrayList<Fragment> list = new ArrayList<>();
        String[] str = {"精彩看点", "直播"};
        areaFragment = new CollectionAreaFragment();
        liveFragment = new CollectionLiveFragment();

        list.add(areaFragment);
        list.add(liveFragment);

        CollectionFragmentAdapetr collectionFragmentAdapetr = new CollectionFragmentAdapetr(getSupportFragmentManager(), list, str);

        collectionViewpager.setAdapter(collectionFragmentAdapetr);

        collectionTab.setupWithViewPager(collectionViewpager);
        collectionTab.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login_backImage, R.id.collection_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_backImage:
                finish();
                break;
            case R.id.collection_edit:
                break;
        }
    }
}
