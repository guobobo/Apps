package com.example.iu.myapplication.module.pandalive.wonderful;


import com.example.iu.myapplication.base.BasePresenter;
import com.example.iu.myapplication.base.BaseView;
import com.example.iu.myapplication.model.entity.WonderfulBean;

public interface WonderfulContarct {
    interface Presenter extends BasePresenter{
        void Addthenumber(String vsid,String n,String serviceId,String o,String of,String p);
    }
    interface View extends BaseView<WonderfulContarct.Presenter>{

        void setResult(WonderfulBean wonderfulBean);
        void setMessage(String msg);
    }
}
