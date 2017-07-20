package com.example.iu.myapplication.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by iu on 2017/6/28.
 */

public class MyHelper {

    private static MyHelper  myHelper = null;
    private final DaoMaster.DevOpenHelper student;

    private MyHelper(Context context) {
        student = new DaoMaster.DevOpenHelper(context, "Student");
    }

    public static synchronized MyHelper gethelper(Context context){

        if(myHelper == null){
            myHelper = new MyHelper(context);
        }

        return myHelper;
    }

    public SQLiteDatabase getw(){

        return student.getWritableDatabase();
    }

    public SQLiteDatabase getr(){

        return student.getReadableDatabase();
    }

}
