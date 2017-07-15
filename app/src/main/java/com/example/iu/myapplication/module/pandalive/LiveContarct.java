package com.example.iu.myapplication.module.pandalive;

import com.example.iu.myapplication.base.BasePresenter;
import com.example.iu.myapplication.base.BaseView;
import com.example.iu.myapplication.model.entity.PandaLiveBean;

/**
 * Created by dell on 2017/7/12.
 */

public interface LiveContarct {

    interface View extends BaseView<LiveContarct.Presenter> {

        void showProgressDialog();
        void dismissDialog();
        void setResult(PandaLiveBean pandaLiveBean);
        void setMessage(String msg);

    }

    interface Presenter extends BasePresenter {

    }
}
