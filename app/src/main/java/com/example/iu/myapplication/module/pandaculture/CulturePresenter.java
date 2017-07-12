package com.example.iu.myapplication.module.pandaculture;

import com.example.iu.myapplication.model.biz.culture.CultureModel;
import com.example.iu.myapplication.model.biz.culture.CultureModelImpl;
import com.example.iu.myapplication.model.entity.CultureBean;
import com.example.iu.myapplication.net.callback.MyNetWorkCallBack;

/**
 * Created by dell on 2017/7/12.
 */

public class CulturePresenter implements CultureContarct.Presenter {

    private CultureModel cultureModel;
    private CultureContarct.View cultureView;

    public CulturePresenter(CultureContarct.View cultureView) {
        this.cultureView = cultureView;
        cultureModel = new CultureModelImpl();
        this.cultureView.setPresenter(this);
    }

    @Override
    public void start() {

        cultureModel.getCultureDate(new MyNetWorkCallBack<CultureBean>() {
            @Override
            public void onSuccess(CultureBean cultureBean) {
                cultureView.setResult(cultureBean);
            }

            @Override
            public void onError(String msg) {
                cultureView.setMessage(msg);
            }
        });
    }
}
