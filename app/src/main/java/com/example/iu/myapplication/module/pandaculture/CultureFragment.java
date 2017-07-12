package com.example.iu.myapplication.module.pandaculture;

import android.view.View;

import com.example.iu.myapplication.base.BaseFragment;
import com.example.iu.myapplication.model.entity.ChinaBean;
import com.example.iu.myapplication.module.chinaLive.ChinaContarct;

/**
 * Created by dell on 2017/7/12.
 */

public class CultureFragment extends BaseFragment implements ChinaContarct.View{
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void loadDate() {

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

    }
}
