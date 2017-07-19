package com.example.iu.myapplication.model.biz.china;

import com.example.iu.myapplication.config.UrlUtils;
import com.example.iu.myapplication.model.entity.ChinaBean;
import com.example.iu.myapplication.net.callback.MyNetWorkCallBack;

/**
 * Created by dell on 2017/7/12.
 */

public class ChinaModelImpl implements ChinaModel {

    @Override
    public void getChinaDate(MyNetWorkCallBack<ChinaBean> callBack) {
        ihttp.get(UrlUtils.CHINAURL,null,callBack);
    }


}
