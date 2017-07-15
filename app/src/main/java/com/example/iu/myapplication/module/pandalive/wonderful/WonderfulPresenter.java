package com.example.iu.myapplication.module.pandalive.wonderful;


import com.example.iu.myapplication.model.biz.live.LiveModel;
import com.example.iu.myapplication.model.biz.live.LiveModelImpl;
import com.example.iu.myapplication.model.entity.WonderfulBean;
import com.example.iu.myapplication.net.callback.MyNetWorkCallBack;

public class WonderfulPresenter implements WonderfulContarct.Presenter {

    private LiveModel liveModel;
    private WonderfulContarct.View wondrefulView;

    public WonderfulPresenter(WonderfulContarct.View wondrefulView) {
        this.wondrefulView = wondrefulView;
        this.wondrefulView.setPresenter(this);
        liveModel=new LiveModelImpl();
    }

    @Override
    public void start() {

    }

    @Override
    public void Addthenumber(String vsid, String n, String serviceId, String o, String of, String p) {
        liveModel.getWonderfulData(vsid, n, serviceId, o, of, p, new MyNetWorkCallBack<WonderfulBean>() {
            @Override
            public void onSuccess(WonderfulBean wonderfulBean) {
                wondrefulView.setResult(wonderfulBean);
            }

            @Override
            public void onError(String msg) {
                wondrefulView.setMessage(msg);
            }
        });
    }
}
