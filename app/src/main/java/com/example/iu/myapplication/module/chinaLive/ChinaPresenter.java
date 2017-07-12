package com.example.iu.myapplication.module.chinaLive;

import com.example.iu.myapplication.model.biz.china.ChinaModel;
import com.example.iu.myapplication.model.biz.china.ChinaModelImpl;
import com.example.iu.myapplication.model.entity.ChinaBean;
import com.example.iu.myapplication.net.callback.MyNetWorkCallBack;

/**
 * Created by dell on 2017/7/12.
 */

public class ChinaPresenter implements ChinaContarct.Presenter {

    private ChinaModel chinaModel;
    private ChinaContarct.View chinaview;

    public ChinaPresenter(ChinaContarct.View chinaview) {
        this.chinaview = chinaview;
        this.chinaview.setPresenter(this);
        chinaModel = new ChinaModelImpl();
    }

    @Override
    public void start() {

        chinaModel.getChinaDate(new MyNetWorkCallBack<ChinaBean>() {
            @Override
            public void onSuccess(ChinaBean chinaBean) {
                chinaview.setResult(chinaBean);
            }

            @Override
            public void onError(String msg) {
                chinaview.setMessage(msg);
            }
        });

    }
}
