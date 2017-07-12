package com.example.iu.myapplication.module.pandaculture;

import com.example.iu.myapplication.base.BasePresenter;
import com.example.iu.myapplication.base.BaseView;
import com.example.iu.myapplication.model.entity.CultureBean;

/**
 * Created by dell on 2017/7/12.
 */

public interface CultureContarct {

    interface View extends BaseView<CultureContarct.Presenter> {

        void showProgressDialog();
        void dismissDialog();
        void setResult(CultureBean cultureBean);
        void setMessage(String msg);

    }

    interface Presenter extends BasePresenter{

    }
}
