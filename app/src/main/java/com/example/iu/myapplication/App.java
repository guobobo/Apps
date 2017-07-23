package com.example.iu.myapplication;

import android.app.Application;

import com.example.iu.myapplication.base.BaseActivity;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

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

        Config.DEBUG = true;

        UMShareAPI.get(this);

        MobclickAgent.openActivityDurationTrack(false);

//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext());
    }
}
