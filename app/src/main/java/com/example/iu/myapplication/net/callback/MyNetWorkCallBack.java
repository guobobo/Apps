package com.example.iu.myapplication.net.callback;

/**
 * Created by dell on 2017/7/12.
 */

public interface MyNetWorkCallBack<T> {

    void onSuccess(T t);
    void onError(String msg);
}
