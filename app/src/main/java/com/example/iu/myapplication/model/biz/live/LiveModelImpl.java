package com.example.iu.myapplication.model.biz.live;

import com.example.iu.myapplication.config.UrlUtils;
import com.example.iu.myapplication.model.entity.LookchatBean;
import com.example.iu.myapplication.model.entity.PandaLiveBean;
import com.example.iu.myapplication.model.entity.PandaMultipleBean;
import com.example.iu.myapplication.model.entity.WonderfulBean;
import com.example.iu.myapplication.net.callback.MyNetWorkCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2017/7/12.
 */

public class LiveModelImpl implements LiveModel {
    @Override
    public void getLiveDate(MyNetWorkCallBack<PandaLiveBean> callBack) {
        //发送首页的网络请求
        ihttp.get(UrlUtils.LIVEURL,null,callBack);
    }

    @Override
    public void getMultipleData(MyNetWorkCallBack<PandaMultipleBean> callBack) {
        ihttp.get(UrlUtils.MULTIPLE,null,callBack);
    }

    @Override
    public void getWonderfulData(String vsid, String n, String serviceId, String o, String of, String p, MyNetWorkCallBack<WonderfulBean> callBack) {
        Map<String,String> map=new HashMap<>();
        map.put("vsid",vsid);
        map.put("n",n);
        map.put("serviceId",serviceId);
        map.put("o",o);
        map.put("of",of);
        map.put("p",p);
        ihttp.get(UrlUtils.Wonderful,map,callBack);
    }

    @Override
    public void getLookchatData(MyNetWorkCallBack<LookchatBean> callBack) {
        ihttp.get(UrlUtils.Lookchar,null,callBack);
    }

}
