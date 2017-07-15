package com.example.iu.myapplication.config;


import android.util.Log;

public class LogUtils {

    private static final boolean ISLOG = true;
    public static void MyLog(String tag, String msg) {
        if (ISLOG) {
            Log.d(tag,msg);
        }
    }
}
