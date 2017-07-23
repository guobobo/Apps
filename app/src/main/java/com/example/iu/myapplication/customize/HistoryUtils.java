package com.example.iu.myapplication.customize;

import android.content.Context;

import com.example.iu.myapplication.model.dao.DaoMaster;
import com.example.iu.myapplication.model.dao.DaoSession;
import com.example.iu.myapplication.model.dao.History;
import com.example.iu.myapplication.model.dao.HistoryDao;
import com.example.iu.myapplication.model.dao.MyHistoryHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dell on 2017/7/22.
 */

public class HistoryUtils {

    private Context context;
    private static HistoryUtils utils;

    private HistoryUtils(Context context){
        this.context = context;
    }

    public static HistoryUtils getInstance(Context context){

        if(utils==null){
            synchronized (HistoryUtils.class){
                if(utils==null){
                    utils = new HistoryUtils(context);
                }

            }
        }

        return utils;
    }

    public void instert(String title,String image ,String videoLength) {

        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = sdf.format(date);

        History history = new History();

        history.setTitle(title);

        history.setImageUrl(image);

        history.setVoideLength(videoLength);

        history.setDayTime(format);

        add(history);
    }

    private void add(History history) {

        DaoMaster master = new DaoMaster(MyHistoryHelper.gethelper(context).getW());

        DaoSession session = master.newSession();

        HistoryDao historyDao = session.getHistoryDao();

        historyDao.insert(history);
    }

}
