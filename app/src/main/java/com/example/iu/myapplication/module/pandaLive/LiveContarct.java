package com.example.iu.myapplication.module.pandaLive;

import com.example.iu.myapplication.base.BasePresenter;
import com.example.iu.myapplication.base.BaseView;
import com.example.iu.myapplication.model.entity.LiveBean;

/**
 * Created by dell on 2017/7/12.
 */

public class LiveContarct {

    interface View extends BaseView<LiveContarct.Presenter> {

        void showProgressDialog();
        void dismissDialog();
        void setResult(LiveBean liveBean);
        void setMessage(String msg);

    }

    interface Presenter extends BasePresenter {

    }
}
