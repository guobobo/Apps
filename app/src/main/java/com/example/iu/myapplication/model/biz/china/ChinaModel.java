package com.example.iu.myapplication.model.biz.china;

import com.example.iu.myapplication.base.BaseModel;
import com.example.iu.myapplication.model.entity.ChinaBean;
import com.example.iu.myapplication.net.callback.MyNetWorkCallBack;

/**
 * Created by dell on 2017/7/12.
 */

public interface ChinaModel extends BaseModel {

    void getChinaDate(MyNetWorkCallBack<ChinaBean> callBack);

}
