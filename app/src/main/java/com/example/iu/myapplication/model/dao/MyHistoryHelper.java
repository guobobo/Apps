package com.example.iu.myapplication.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by iu on 2017/6/28.
 */

public class MyHistoryHelper {

    private static MyHistoryHelper myHelper = null;
    private final DaoMaster.DevOpenHelper history;

    private MyHistoryHelper(Context context) {
        history = new DaoMaster.DevOpenHelper(context, "history");
    }

    public static synchronized MyHistoryHelper gethelper(Context context){

        if(myHelper == null){
            myHelper = new MyHistoryHelper(context);
        }

        return myHelper;
    }

    public SQLiteDatabase getW(){

        return history.getWritableDatabase();
    }

    public SQLiteDatabase getR(){

        return history.getReadableDatabase();
    }
}
