package com.example.iu.myapplication.net;

/**
 * Created by dell on 2017/7/12.
 */

public class HttpFactory {

    public static IHttp httpCreate(){

        return OkHttpUtils.getInstance();
    }
}
