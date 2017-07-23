package com.example.iu.myapplication.module.home;

import com.example.iu.myapplication.App;
import com.example.iu.myapplication.config.LogUtils;
import com.example.iu.myapplication.model.biz.home.HomeModel;
import com.example.iu.myapplication.model.biz.home.HomeModelImpl;
import com.example.iu.myapplication.model.entity.HomeBean;
import com.example.iu.myapplication.model.entity.UpdateBean;
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

    @Override
    public void getversion() {

            homeModel.version(new MyNetWorkCallBack<UpdateBean>() {
            @Override
            public void onSuccess(final UpdateBean updateBean) {
                //成功的回调
                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        homeview.getVersion(updateBean);
                        LogUtils.MyLog("版本更新bean打印",updateBean.getData().getVersionsinfo().toString());

                    }
                });
            }

                @Override
                public void onError(String msg) {
                    LogUtils.MyLog("版本更新网络请求错误",msg);
                }

        });

    }
}
