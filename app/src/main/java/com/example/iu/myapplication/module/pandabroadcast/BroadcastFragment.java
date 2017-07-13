package com.example.iu.myapplication.module.pandabroadcast;

import android.view.View;

import com.example.iu.myapplication.R;
import com.example.iu.myapplication.base.BaseFragment;
import com.example.iu.myapplication.model.entity.BroadCastBean;

/**
 * Created by dell on 2017/7/12.
 */

public class BroadcastFragment extends BaseFragment implements BroadcastContarct.View{

    @Override
    public int getLayoutId() {
        return R.layout.fragment_pandabroadcast;
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
    public void setResult(BroadCastBean broadCastBean) {

    }

    @Override
    public void setMessage(String msg) {

    }
    @Override
    public void setPresenter(BroadcastContarct.Presenter presenter) {

    }
}
