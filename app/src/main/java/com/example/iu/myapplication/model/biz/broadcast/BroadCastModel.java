package com.example.iu.myapplication.model.biz.broadcast;

import com.example.iu.myapplication.base.BaseModel;
import com.example.iu.myapplication.model.entity.BroadCastBean;
import com.example.iu.myapplication.net.callback.MyNetWorkCallBack;

/**
 * Created by dell on 2017/7/12.
 */

public interface BroadCastModel extends BaseModel {

    void getBroadCastDate(MyNetWorkCallBack<BroadCastBean> callBack);
}
