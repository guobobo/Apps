package com.example.iu.myapplication.module.pandabroadcast;

import com.example.iu.myapplication.base.BasePresenter;
import com.example.iu.myapplication.base.BaseView;
import com.example.iu.myapplication.model.entity.BroadCastBean;

/**
 * Created by dell on 2017/7/12.
 */

public interface BroadcastContarct {

    interface View extends BaseView<BroadcastContarct.Presenter> {

        void showProgressDialog();
        void dismissDialog();
        void setResult(BroadCastBean broadCastBean);
        void setMessage(String msg);

    }

    interface Presenter extends BasePresenter{



    }
}
