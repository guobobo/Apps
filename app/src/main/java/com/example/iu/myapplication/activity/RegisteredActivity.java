package com.example.iu.myapplication.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.iu.myapplication.R;
import com.example.iu.myapplication.adapter.CollectionFragmentAdapetr;
import com.example.iu.myapplication.base.BaseActivity;
import com.example.iu.myapplication.customize.CustomViewPager;
import com.example.iu.myapplication.fargment.MailBoxFragment;
import com.example.iu.myapplication.fargment.PhoneFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class RegisteredActivity extends BaseActivity {

    @Bind(R.id.login_backImage)
    ImageView loginBackImage;
    @Bind(R.id.registered_tab)
    TabLayout registeredTab;
    @Bind(R.id.registered_viewpager)
    CustomViewPager registeredViewpager;
    @Bind(R.id.activity_registered)
    LinearLayout activityRegistered;

    @Override
    public int getLayoutId() {
        return R.layout.activity_registered;
    }

    @Override
    public void initView() {

        PhoneFragment phoneFragment = new PhoneFragment();
        MailBoxFragment mailBoxFragment = new MailBoxFragment();

        ArrayList<Fragment> list = new ArrayList<Fragment>();
        list.add(phoneFragment);
        list.add(mailBoxFragment);
        String[] strings = {"手机注册","邮箱注册"};

        CollectionFragmentAdapetr collectionFragmentAdapetr = new CollectionFragmentAdapetr(getSupportFragmentManager(), list, strings);

        registeredViewpager.setAdapter(collectionFragmentAdapetr);

        registeredTab.setupWithViewPager(registeredViewpager);
        registeredTab.setTabMode(TabLayout.MODE_FIXED);

    }

    @OnClick(R.id.login_backImage)
    public void onViewClicked() {
        finish();
    }
}
