package com.example.iu.myapplication.net;

import com.example.iu.myapplication.net.callback.MyNetWorkCallBack;

import java.util.Map;

/**
 * Created by dell on 2017/7/12.
 */

public interface IHttp {

    <T> void get(String url , Map<String ,String> params , MyNetWorkCallBack<T> callBack);
    <T> void post(String url , Map<String ,String> params , MyNetWorkCallBack<T> callBack);

    void upLoad();

    void downLoad();

    void imageLoad();

}
