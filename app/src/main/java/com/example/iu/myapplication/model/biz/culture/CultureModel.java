package com.example.iu.myapplication.model.biz.culture;

import com.example.iu.myapplication.base.BaseModel;
import com.example.iu.myapplication.model.entity.CultureBean;
import com.example.iu.myapplication.net.callback.MyNetWorkCallBack;

/**
 * Created by dell on 2017/7/12.
 */

public interface CultureModel extends BaseModel {

    void getCultureDate(MyNetWorkCallBack<CultureBean> callBack);

}
