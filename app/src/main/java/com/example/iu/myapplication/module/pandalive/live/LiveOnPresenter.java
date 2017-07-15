package com.example.iu.myapplication.module.pandalive.live;


import com.example.iu.myapplication.model.biz.live.LiveModel;
import com.example.iu.myapplication.model.biz.live.LiveModelImpl;
import com.example.iu.myapplication.model.entity.PandaLiveBean;
import com.example.iu.myapplication.model.entity.PandaMultipleBean;
import com.example.iu.myapplication.net.callback.MyNetWorkCallBack;

public class LiveOnPresenter implements LiveOnContarct.Presenter {
    private LiveModel liveModel ;
    private LiveOnContarct.View liveonView;

    public LiveOnPresenter(LiveOnContarct.View liveonView) {
        this.liveonView = liveonView;
        this.liveonView.setPresenter(this);
        liveModel=new LiveModelImpl();
    }

    @Override
    public void start() {
        liveModel.getLiveDate(new MyNetWorkCallBack<PandaLiveBean>() {
            @Override
            public void onSuccess(PandaLiveBean pandaLiveBean) {
                liveonView.setResult(pandaLiveBean);
            }

            @Override
            public void onError(String msg) {
                liveonView.setMessage(msg);
            }

        });
    }

    @Override
    public void multiple() {
        liveModel.getMultipleData(new MyNetWorkCallBack<PandaMultipleBean>() {
            @Override
            public void onSuccess(PandaMultipleBean multipleBean) {
                liveonView.setMultiple(multipleBean);
            }

            @Override
            public void onError(String msg) {
                liveonView.setMessage(msg);
            }
        });
    }
}
