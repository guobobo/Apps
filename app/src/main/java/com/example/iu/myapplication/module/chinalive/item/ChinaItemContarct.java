package com.example.iu.myapplication.module.chinalive.item;


import com.example.iu.myapplication.base.BasePresenter;
import com.example.iu.myapplication.base.BaseView;

public interface ChinaItemContarct {
    interface Presenter extends BasePresenter{

    }
    interface View extends BaseView<ChinaItemContarct.Presenter>{

        void setMessage(String msg);

    }
}
