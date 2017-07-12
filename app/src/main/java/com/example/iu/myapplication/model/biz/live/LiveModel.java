package com.example.iu.myapplication.model.biz.live;

import com.example.iu.myapplication.base.BaseModel;
import com.example.iu.myapplication.model.entity.LiveBean;
import com.example.iu.myapplication.net.callback.MyNetWorkCallBack;

/**
 * Created by dell on 2017/7/12.
 */

public interface LiveModel extends BaseModel {

    void getHomeDate(MyNetWorkCallBack<LiveBean> callBack);

}
