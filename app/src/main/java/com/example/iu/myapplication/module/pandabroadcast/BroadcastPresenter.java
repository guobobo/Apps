package com.example.iu.myapplication.module.pandabroadcast;

import com.example.iu.myapplication.model.biz.broadcast.BroadCastModel;
import com.example.iu.myapplication.model.biz.broadcast.BroadCastModelImpl;
import com.example.iu.myapplication.model.entity.BroadCastBean;
import com.example.iu.myapplication.net.callback.MyNetWorkCallBack;

/**
 * Created by dell on 2017/7/12.
 */

public class BroadcastPresenter implements BroadcastContarct.Presenter {

    private BroadCastModel broadCastModel;
    private BroadcastContarct.View broadcastview;

    public BroadcastPresenter(BroadcastContarct.View broadcastview) {

        this.broadcastview = broadcastview;
        broadCastModel = new BroadCastModelImpl();
        this.broadcastview.setPresenter(this);

    }

    @Override
    public void start() {
        broadCastModel.getBroadCastDate(new MyNetWorkCallBack<BroadCastBean>() {
            @Override
            public void onSuccess(BroadCastBean broadCastBean) {
                broadcastview.setResult(broadCastBean);
            }

            @Override
            public void onError(String msg) {
                broadcastview.setMessage(msg);
            }
        });
    }
}
