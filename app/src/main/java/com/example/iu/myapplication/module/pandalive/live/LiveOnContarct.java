package com.example.iu.myapplication.module.pandalive.live;


import com.example.iu.myapplication.base.BasePresenter;
import com.example.iu.myapplication.base.BaseView;
import com.example.iu.myapplication.model.entity.LookchatBean;
import com.example.iu.myapplication.model.entity.PandaLiveBean;
import com.example.iu.myapplication.model.entity.PandaMultipleBean;

public interface LiveOnContarct {
    interface Presenter extends BasePresenter{
        void multiple();
        void lookchat();
    }
    interface View extends BaseView<LiveOnContarct.Presenter>{
        void setMultiple(PandaMultipleBean pandaMultipleBean);
        void setResult(PandaLiveBean pandaLiveBean);
        void setMessage(String msg);
        void setLookchat(LookchatBean lookchatBean);
    }

}
