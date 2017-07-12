package com.example.iu.myapplication.module.chinaLive;

import com.example.iu.myapplication.base.BasePresenter;
import com.example.iu.myapplication.base.BaseView;
import com.example.iu.myapplication.model.entity.ChinaBean;

/**
 * Created by dell on 2017/7/12.
 */

public interface ChinaContarct {

    interface View extends BaseView<ChinaContarct.Presenter> {

        void showProgressDialog();
        void dismissDialog();
        void setResult(ChinaBean chinaBean);
        void setMessage(String msg);

    }

    interface Presenter extends BasePresenter {



    }

}
