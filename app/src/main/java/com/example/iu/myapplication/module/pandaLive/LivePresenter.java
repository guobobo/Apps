package com.example.iu.myapplication.module.pandaLive;

import com.example.iu.myapplication.model.biz.live.LiveModel;
import com.example.iu.myapplication.model.biz.live.LiveModelImpl;
import com.example.iu.myapplication.model.entity.LiveBean;
import com.example.iu.myapplication.net.callback.MyNetWorkCallBack;

/**
 * Created by dell on 2017/7/12.
 */

public class LivePresenter implements LiveContarct.Presenter {

    private LiveModel liveModel ;
    private LiveContarct.View liveView;

    public LivePresenter(LiveContarct.View liveView) {
        this.liveView = liveView;
        liveModel = new LiveModelImpl();
        this.liveView.setPresenter(this);
    }

    @Override
    public void start() {

        liveModel.getHomeDate(new MyNetWorkCallBack<LiveBean>() {
            @Override
            public void onSuccess(LiveBean liveBean) {
                liveView.setResult(liveBean);
            }

            @Override
            public void onError(String msg) {
                liveView.setMessage(msg);
            }
        });

    }
}
