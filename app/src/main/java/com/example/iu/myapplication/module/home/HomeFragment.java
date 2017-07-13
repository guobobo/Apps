package com.example.iu.myapplication.module.home;

import android.view.View;

import com.example.iu.myapplication.R;
import com.example.iu.myapplication.base.BaseFragment;
import com.example.iu.myapplication.model.entity.HomeBean;

/**
 * Created by dell on 2017/7/12.
 */

public class HomeFragment extends BaseFragment implements HomeContarct.View{

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
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
    public void setResult(HomeBean homeBean) {

    }

    @Override
    public void setMessage(String msg) {

    }

    @Override
    public void setPresenter(HomeContarct.Presenter presenter) {

    }
}
