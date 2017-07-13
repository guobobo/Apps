package com.example.iu.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.iu.myapplication.base.BaseActivity;
import com.example.iu.myapplication.module.chinalive.ChinaFragment;
import com.example.iu.myapplication.module.home.HomeFragment;
import com.example.iu.myapplication.module.home.HomePresenter;
import com.example.iu.myapplication.module.pandabroadcast.BroadcastFragment;
import com.example.iu.myapplication.module.pandaculture.CultureFragment;
import com.example.iu.myapplication.module.pandalive.LiveFragment;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.linearimage)
    LinearLayout linearimage;
    @Bind(R.id.tab_image)
    ImageView tabImage;
    @Bind(R.id.tab_tv1)
    TextView tabTv1;
    @Bind(R.id.tab_image1)
    ImageView tabImage1;
    @Bind(R.id.tab_user)
    ImageView tabUser;
    @Bind(R.id.main_home)
    RadioButton mainHome;
    @Bind(R.id.main_Pandalive)
    RadioButton mainPandalive;
    @Bind(R.id.main_Rollingvideo)
    RadioButton mainRollingvideo;
    @Bind(R.id.main_Pandabroadcast)
    RadioButton mainPandabroadcast;
    @Bind(R.id.main_liveCN)
    RadioButton mainLiveCN;
    @Bind(R.id.linear)
    LinearLayout linear;
    @Bind(R.id.main_viewpager)
    FrameLayout mainViewpager;

    private HomeFragment homeFragment;
    private LiveFragment liveFragment;
    private CultureFragment cultureFragment;
    private BroadcastFragment broadcastFragme;
    private ChinaFragment chinaFragment;

    Timer timer;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 123:
                    linearimage.setVisibility(View.GONE);
                    linear.setVisibility(View.VISIBLE);
                    timer.cancel();
                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(123);
                    }
                }, 3000, 3000);
            }
        };
        thread.start();
        //初始首页页面
        Setinitial();
    }

    @OnClick({R.id.main_home, R.id.main_Pandalive, R.id.main_Rollingvideo, R.id.main_Pandabroadcast, R.id.main_liveCN})
    public void onViewClicked(View view) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //如果有了fragment就让他先隐藏
        hind_show(transaction);

        switch (view.getId()) {
            case R.id.main_home:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.main_viewpager, homeFragment);
                    transaction.show(homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                tabImage.setVisibility(View.VISIBLE);
                tabImage1.setVisibility(View.VISIBLE);
                tabTv1.setVisibility(View.GONE);
                break;
            case R.id.main_Pandalive:
                if (liveFragment == null) {
                    liveFragment = new LiveFragment();
                    transaction.add(R.id.main_viewpager, liveFragment);
                    transaction.show(liveFragment);
                } else {
                    transaction.show(liveFragment);
                }
                tabImage.setVisibility(View.GONE);
                tabImage1.setVisibility(View.GONE);
                tabTv1.setText("熊猫直播");
                tabTv1.setVisibility(View.VISIBLE);
                break;
            case R.id.main_Rollingvideo:
                if (cultureFragment == null) {
                    cultureFragment = new CultureFragment();
                    transaction.add(R.id.main_viewpager, cultureFragment);
                    transaction.show(cultureFragment);
                } else {
                    transaction.show(cultureFragment);
                }
                tabImage.setVisibility(View.GONE);
                tabImage1.setVisibility(View.GONE);
                tabTv1.setText("熊猫文化");
                tabTv1.setVisibility(View.VISIBLE);
                break;
            case R.id.main_Pandabroadcast:
                if (broadcastFragme == null) {
                    broadcastFragme = new BroadcastFragment();
                    transaction.add(R.id.main_viewpager, broadcastFragme);
                    transaction.show(broadcastFragme);
                } else {
                    transaction.show(broadcastFragme);
                }
                tabImage.setVisibility(View.GONE);
                tabImage1.setVisibility(View.GONE);
                tabTv1.setText("熊猫播报");
                tabTv1.setVisibility(View.VISIBLE);
                break;
            case R.id.main_liveCN:
                if (chinaFragment == null) {
                    chinaFragment = new ChinaFragment();
                    transaction.add(R.id.main_viewpager, chinaFragment);
                    transaction.show(chinaFragment);
                } else {
                    transaction.show(chinaFragment);
                }
                tabImage.setVisibility(View.GONE);
                tabImage1.setVisibility(View.GONE);
                tabTv1.setText("直播中国");
                tabTv1.setVisibility(View.VISIBLE);
                break;
        }
        transaction.commit();
    }

    public void hind_show(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (liveFragment != null) {
            transaction.hide(liveFragment);
        }
        if (cultureFragment != null) {
            transaction.hide(cultureFragment);
        }
        if (broadcastFragme != null) {
            transaction.hide(broadcastFragme);
        }
        if (chinaFragment != null) {
            transaction.hide(chinaFragment);
        }
    }

    //      加载初始布局的方法
    private void Setinitial() {
        homeFragment = new HomeFragment();
        HomePresenter homePresenter = new HomePresenter(homeFragment);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.main_viewpager, homeFragment);
        transaction.commit();

    }

}
