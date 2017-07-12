package com.example.iu.myapplication.model.biz.home;

import com.example.iu.myapplication.base.BaseModel;
import com.example.iu.myapplication.model.entity.HomeBean;
import com.example.iu.myapplication.net.callback.MyNetWorkCallBack;

/**
 * Created by dell on 2017/7/12.
 */

public interface HomeModel extends BaseModel {

    void getHomeDate(MyNetWorkCallBack<HomeBean> callBack);

}
