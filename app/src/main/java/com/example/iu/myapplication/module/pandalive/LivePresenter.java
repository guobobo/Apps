package com.example.iu.myapplication.module.pandalive;

import com.example.iu.myapplication.model.biz.live.LiveModel;
import com.example.iu.myapplication.model.biz.live.LiveModelImpl;
import com.example.iu.myapplication.model.entity.PandaLiveBean;
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

        liveModel.getLiveDate(new MyNetWorkCallBack<PandaLiveBean>() {
            @Override
            public void onSuccess(PandaLiveBean pandaLiveBean) {
                liveView.setResult(pandaLiveBean);
            }

            @Override
            public void onError(String msg) {
                liveView.setMessage(msg);
            }
        });

    }
}
