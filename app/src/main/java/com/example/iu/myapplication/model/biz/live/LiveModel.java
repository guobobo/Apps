package com.example.iu.myapplication.model.biz.live;

import com.example.iu.myapplication.base.BaseModel;
import com.example.iu.myapplication.model.entity.PandaLiveBean;
import com.example.iu.myapplication.model.entity.PandaMultipleBean;
import com.example.iu.myapplication.model.entity.WonderfulBean;
import com.example.iu.myapplication.net.callback.MyNetWorkCallBack;

/**
 * Created by dell on 2017/7/12.
 */

public interface LiveModel extends BaseModel {

    void getLiveDate(MyNetWorkCallBack<PandaLiveBean> callBack);
    void getMultipleData(MyNetWorkCallBack<PandaMultipleBean> callBack);

    void getWonderfulData(String vsid,String n,String serviceId,String o,String of,String p,MyNetWorkCallBack<WonderfulBean> callBack);
}
