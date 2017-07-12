package com.example.iu.myapplication.module.home;

import com.example.iu.myapplication.base.BasePresenter;
import com.example.iu.myapplication.base.BaseView;
import com.example.iu.myapplication.model.entity.HomeBean;

/**
 * Created by dell on 2017/7/12.
 */

public interface HomeContarct {

    interface View extends BaseView<HomeContarct.Presenter>{

        void showProgressDialog();
        void dismissDialog();
        void setResult(HomeBean homeBean);
        void setMessage(String msg);

    }

    interface Presenter extends BasePresenter{



    }
}
