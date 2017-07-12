package com.example.iu.myapplication.module.home;

import com.example.iu.myapplication.model.biz.home.HomeModel;
import com.example.iu.myapplication.model.biz.home.HomeModelImpl;
import com.example.iu.myapplication.model.entity.HomeBean;
import com.example.iu.myapplication.net.callback.MyNetWorkCallBack;

/**
 * Created by dell on 2017/7/12.
 */

public class HomePresenter implements HomeContarct.Presenter {

    private HomeModel homeModel;
    private HomeContarct.View homeview;

    public HomePresenter(HomeContarct.View homeview) {
        this.homeview = homeview;
        this.homeview.setPresenter(this);
        homeModel = new HomeModelImpl();
    }

    @Override
    public void start() {

        homeModel.getHomeDate(new MyNetWorkCallBack<HomeBean>() {
            @Override
            public void onSuccess(HomeBean homeBean) {
                homeview.setResult(homeBean);
            }

            @Override
            public void onError(String msg) {
                homeview.setMessage(msg);
            }
        });

    }
}
