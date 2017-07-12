package com.example.iu.myapplication.base;

import com.example.iu.myapplication.net.HttpFactory;
import com.example.iu.myapplication.net.IHttp;

/**
 * Created by dell on 2017/7/12.
 */

//model的基类  用于获得OKhttp工具类的实例

public interface BaseModel {

    public static IHttp ihttp = HttpFactory.httpCreate();
}
