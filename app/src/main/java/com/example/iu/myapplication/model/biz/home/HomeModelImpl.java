package com.example.iu.myapplication.model.biz.home;

import com.example.iu.myapplication.config.UrlUtils;
import com.example.iu.myapplication.model.entity.HomeBean;
import com.example.iu.myapplication.model.entity.UpdateBean;
import com.example.iu.myapplication.net.callback.MyNetWorkCallBack;

/**
 * Created by dell on 2017/7/12.
 */

public class HomeModelImpl implements HomeModel {

    @Override
    public void getHomeDate(MyNetWorkCallBack<HomeBean> callBack) {

        //发送首页的网络请求
        ihttp.get(UrlUtils.HOMEURL,null,callBack);
    }
    @Override
    public void version(MyNetWorkCallBack<UpdateBean> callBack) {
        ihttp.get(UrlUtils.UPDATE_URL,null,callBack);
    }
}
