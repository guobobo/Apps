package com.example.iu.myapplication.module.pandabroadcast.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.iu.myapplication.R;
import com.example.iu.myapplication.config.LogUtils;
import com.example.iu.myapplication.config.UrlUtils;
import com.example.iu.myapplication.model.dao.DaoMaster;
import com.example.iu.myapplication.model.dao.DaoSession;
import com.example.iu.myapplication.model.dao.MyHelper;
import com.example.iu.myapplication.model.dao.Work;
import com.example.iu.myapplication.model.dao.WorkDao;
import com.example.iu.myapplication.model.entity.VoideBean;
import com.google.gson.Gson;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCFullScreenActivity;
import fm.jiecao.jcvideoplayer_lib.PandaVedioPlayer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class BroadcastSpActivity extends AppCompatActivity {


    private PandaVedioPlayer my_JCV;
    private String title,data,duration,url,image;
    private String id;
    private String video_url;
    private String VideoBean_url;
    final int HANDLERMSG = 1;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case HANDLERMSG:
                    initView();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_sp);


        title = getIntent().getStringExtra("title");
        image = getIntent().getStringExtra("image");
        data = getIntent().getStringExtra("data");
        duration = getIntent().getStringExtra("duration");
        id = getIntent().getStringExtra("id");
        VideoBean_url = UrlUtils.VIDEO + id;

        getOk();



    }

    private void initView() {
        my_JCV = (PandaVedioPlayer) findViewById(R.id.my_JCV);
        JCFullScreenActivity.toActivity(this,
                video_url, null,
                PandaVedioPlayer.class, title);

        my_JCV.btn_share.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                UMVideo video = new UMVideo("http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4");
                video.setTitle(title);//视频的标题
                video.setThumb(new UMImage(BroadcastSpActivity.this,image));//视频的缩略图
                video.setDescription(data);//视频的描述

                new ShareAction(BroadcastSpActivity.this).withText(title)  .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN).setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {

                    }
                }).withMedia(video).open();
            }
        });

        BroadcastSpActivity.this.finish();
    }



    public void add(Work work) {

        DaoMaster master = new DaoMaster(MyHelper.gethelper(BroadcastSpActivity.this).getw());
        DaoSession daoSession = master.newSession();
        WorkDao workDao = daoSession.getWorkDao();
        workDao.insert(work);
    }

    public void delete(Work work) {

        DaoMaster master = new DaoMaster(MyHelper.gethelper(BroadcastSpActivity.this).getw());
        DaoSession daoSession = master.newSession();
        WorkDao workDao = daoSession.getWorkDao();
        workDao.delete(work);
    }

    public List<Work> selec() {

        DaoMaster master = new DaoMaster(MyHelper.gethelper(BroadcastSpActivity.this).getr());
        DaoSession daoSession = master.newSession();
        WorkDao workDao = daoSession.getWorkDao();

        QueryBuilder<Work> workQueryBuilder = workDao.queryBuilder();
        List<Work> list = workQueryBuilder.list();

        return list;
    }

    public void getOk(){

        new Thread() {

            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(VideoBean_url).build();
                    Response respons = client.newCall(request).execute();
                    String string = respons.body().string();
                    LogUtils.MyLog("TAG","---------"+string);

                    Gson gson = new Gson();
                    VoideBean voideBean = gson.fromJson(string, VoideBean.class);
                    video_url = voideBean.getVideo().getChapters3().get(0).getUrl();
                    LogUtils.MyLog("TAG","sssssssssssssssssss——————"+video_url);


                    handler.sendEmptyMessage(HANDLERMSG);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }.start();

    }

}
