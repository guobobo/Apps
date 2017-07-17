package com.example.iu.myapplication.module.chinalive;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.iu.myapplication.R;
import com.example.iu.myapplication.base.BaseFragment;
import com.example.iu.myapplication.customize.CustomViewPager;
import com.example.iu.myapplication.model.entity.ChinaBean;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by dell on 2017/7/12.
 */

public class ChinaFragment extends BaseFragment implements ChinaContarct.View {

    @Bind(R.id.china_tab)
    TabLayout chinaTab;
    @Bind(R.id.live_china_add_channel)
    ImageView liveChinaAddChannel;
    @Bind(R.id.china_layout)
    CustomViewPager chinaLayout;
    private ChinaContarct.Presenter presenter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_livecn;
    }

    @Override
    public void initView(View view) {

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
    public void setResult(ChinaBean chinaBean) {

    }

    @Override
    public void setMessage(String msg) {

    }

    @Override
    public void setPresenter(ChinaContarct.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
