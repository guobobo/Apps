package com.example.iu.myapplication.model.biz.broadcast;

import com.example.iu.myapplication.config.UrlUtils;
import com.example.iu.myapplication.model.entity.BroadCastBean;
import com.example.iu.myapplication.model.entity.BroadCastListBean;
import com.example.iu.myapplication.net.callback.MyNetWorkCallBack;

/**
 * Created by dell on 2017/7/12.
 */

public class BroadCastModelImpl implements BroadCastModel {

    @Override
    public void getBroadCastDate(MyNetWorkCallBack<BroadCastBean> callBack) {
        ihttp.get(UrlUtils.BROADCASTURL,null,callBack);
    }

    @Override
    public void getBroadCastListDate(MyNetWorkCallBack<BroadCastListBean> callBack) {
        ihttp.get(UrlUtils.BROADCASTURLLIST,null,callBack);

    }
}
