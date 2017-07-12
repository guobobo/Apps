package com.example.iu.myapplication.model.biz.live;

import com.example.iu.myapplication.config.UrlUtils;
import com.example.iu.myapplication.model.entity.LiveBean;
import com.example.iu.myapplication.net.callback.MyNetWorkCallBack;

/**
 * Created by dell on 2017/7/12.
 */

public class LiveModelImpl implements LiveModel {
    @Override
    public void getHomeDate(MyNetWorkCallBack<LiveBean> callBack) {

        //发送首页的网络请求
        ihttp.get(UrlUtils.LIVEURL,null,callBack);
    }
}
