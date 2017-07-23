package com.example.iu.myapplication;

import android.app.Application;

import com.example.iu.myapplication.base.BaseActivity;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.PlatformConfig;

/**
 * Created by dell on 2017/5/24.
 */

public class App extends Application {

    {
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("1106204691", "KEguRaDKDSPfp7tH");
        PlatformConfig.setSinaWeibo("1770387667", "96551cfcd196b8bfa51d316b49a1bc42", "http://sns.whalecloud.com");
    }

    public static BaseActivity context = null;

    @Override
    public void onCreate() {
        super.onCreate();

        PushAgent mPushAgent = PushAgent.getInstance(this);
//注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
//                LogUtils.MyLog("TAG","deviceToken-------"+deviceToken);
//                Log.e("TAG","-------------------------------"+deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });

    }
}
