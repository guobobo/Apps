package com.example.iu.myapplication.module.pandalive;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;

import com.example.iu.myapplication.R;
import com.example.iu.myapplication.base.BaseFragment;
import com.example.iu.myapplication.customize.CustomViewPager;
import com.example.iu.myapplication.model.entity.PandaLiveBean;
import com.example.iu.myapplication.module.pandalive.adapter.LivePagerAdapter;
import com.example.iu.myapplication.module.pandalive.live.LiveOnFragment;
import com.example.iu.myapplication.module.pandalive.live.LiveOnPresenter;
import com.example.iu.myapplication.module.pandalive.wonderful.WonderfulFragment;
import com.example.iu.myapplication.module.pandalive.wonderful.WonderfulPresenter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by dell on 2017/7/12.
 */

public class LiveFragment extends BaseFragment implements LiveContarct.View {

    @Bind(R.id.panda_live_tab)
    TabLayout pandaLiveTab;
    @Bind(R.id.panda_live_pager)
    CustomViewPager pandaLivePager;

    private LiveContarct.Presenter presenter;
    private List<Fragment> fragmentList;
    private WonderfulFragment liveonFragment1;
    private WonderfulFragment liveonFragment2;
    private WonderfulFragment liveonFragment3;
    private WonderfulFragment liveonFragment4;
    private WonderfulFragment liveonFragment5;
    private WonderfulFragment liveonFragment6;
    private WonderfulFragment liveonFragment7;
    private WonderfulFragment liveonFragment8;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_pandalive;
    }

    @Override
    public void initView(View view) {
        fragmentList=new ArrayList<>();
        setPageFragment();
        LivePagerAdapter livePagerAdapter=new LivePagerAdapter(getActivity().getSupportFragmentManager(),fragmentList);
        pandaLivePager.setAdapter(livePagerAdapter);
        pandaLivePager.setScanScroll(false);
        pandaLiveTab.setupWithViewPager(pandaLivePager);
        pandaLiveTab.setTabMode(TabLayout.MODE_SCROLLABLE);

        LinearLayout linearLayout = (LinearLayout) pandaLiveTab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getContext(),
                R.drawable.radio_bg));

        for (int i = 0; i < pandaLiveTab.getTabCount(); i++) {
            TabLayout.Tab tab = pandaLiveTab.getTabAt(i);
            if (tab == null) return;
            Class c = tab.getClass();
            try {
                Field field = c.getDeclaredField("mView");

                field.setAccessible(true);
                view = (View) field.get(tab);
                if (view == null) return;
                view.setTag(i);
                final View finalView = view;
                view.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        int position = (int) finalView.getTag();
                        switch (position){
                            case 1:
                                liveonFragment1.setVSID("VSET100167216881");
                                break;
                            case 2:
                                liveonFragment1.setVSID("VSET100332640004");
                                break;
                            case 3:
                                liveonFragment1.setVSID("VSET100272959126");
                                break;
                            case 4:
                                liveonFragment1.setVSID("VSET100340574858");
                                break;
                            case 5:
                                liveonFragment1.setVSID("VSET100284428835");
                                break;
                            case 6:
                                liveonFragment1.setVSID("VSET100237714751");
                                break;
                            case 7:
                                liveonFragment1.setVSID("VSET100167308855");
                                break;
                            case 8:
                                liveonFragment1.setVSID("VSET100219009515");
                                break;
                        }

                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void loadDate() {
        presenter.start();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void setResult(PandaLiveBean pandaLiveBean) {

    }

    @Override
    public void setMessage(String msg) {

    }

    @Override
    public void setPresenter(LiveContarct.Presenter presenter) {
        this.presenter=presenter;
    }

    public void setPageFragment() {
        LiveOnFragment liveonFragment=new LiveOnFragment();
        liveonFragment1 = new WonderfulFragment();
        liveonFragment2 = new WonderfulFragment();
        liveonFragment3 = new WonderfulFragment();
        liveonFragment4 = new WonderfulFragment();
        liveonFragment5 = new WonderfulFragment();
        liveonFragment6 = new WonderfulFragment();
        liveonFragment7 = new WonderfulFragment();
        liveonFragment8 = new WonderfulFragment();
        fragmentList.add(liveonFragment);
        fragmentList.add(liveonFragment1);
        fragmentList.add(liveonFragment2);
        fragmentList.add(liveonFragment3);
        fragmentList.add(liveonFragment4);
        fragmentList.add(liveonFragment5);
        fragmentList.add(liveonFragment6);
        fragmentList.add(liveonFragment7);
        fragmentList.add(liveonFragment8);

        new LiveOnPresenter(liveonFragment);
        new WonderfulPresenter(liveonFragment1);
        new WonderfulPresenter(liveonFragment2);
        new WonderfulPresenter(liveonFragment3);
        new WonderfulPresenter(liveonFragment4);
        new WonderfulPresenter(liveonFragment5);
        new WonderfulPresenter(liveonFragment6);
        new WonderfulPresenter(liveonFragment7);
        new WonderfulPresenter(liveonFragment8);
    }

}
