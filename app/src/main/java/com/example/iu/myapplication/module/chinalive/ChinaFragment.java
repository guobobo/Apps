package com.example.iu.myapplication.module.chinalive;

import android.view.View;

import com.example.iu.myapplication.R;
import com.example.iu.myapplication.base.BaseFragment;
import com.example.iu.myapplication.model.entity.ChinaBean;

/**
 * Created by dell on 2017/7/12.
 */

public class ChinaFragment extends BaseFragment implements  ChinaContarct.View{

    @Override
    public int getLayoutId() {
        return R.layout.fragment_livecn;
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
