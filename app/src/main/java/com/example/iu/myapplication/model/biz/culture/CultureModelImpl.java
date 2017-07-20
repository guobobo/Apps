package com.example.iu.myapplication.model.biz.culture;

import com.example.iu.myapplication.config.UrlUtils;
import com.example.iu.myapplication.model.entity.CultureBean;
import com.example.iu.myapplication.net.callback.MyNetWorkCallBack;

/**
 * Created by dell on 2017/7/12.
 */

public class CultureModelImpl implements CultureModel {

    @Override
    public void getCultureDate(MyNetWorkCallBack<CultureBean> callBack) {
        ihttp.get(UrlUtils.CULTUREURL,null,callBack);
    }

}
