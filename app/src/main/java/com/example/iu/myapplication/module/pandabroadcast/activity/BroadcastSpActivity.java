package com.example.iu.myapplication.module.pandabroadcast.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

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

import fm.jiecao.jcvideoplayer_lib.JCUserAction;
import fm.jiecao.jcvideoplayer_lib.JCUserActionStandard;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class BroadcastSpActivity extends AppCompatActivity  implements JCVideoPlayerStandards.MyOnClikListener{


    private String title, data, duration, url, image;
    private String id;
    private String video_url;
    private String VideoBean_url;
    private String video_url1;
    final int HANDLERMSG = 1;
    private BroadcastSpActivity mActivity;

    private String SPURL = "";

    private ImageView img;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
               case HANDLERMSG:
                    if(SPURL.equals("")) {
                        SPURL=video_url;
                    }

                    initView();
                    break;
            }
        }
    };
    private Work work;


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
        JCVideoPlayerStandards myjcv =  (JCVideoPlayerStandards) findViewById(R.id.myjcv);
        myjcv.setUp(SPURL
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, title);
        myjcv.thumbImageView.setImageResource(R.drawable._no_img);

        myjcv.setListener(this);
        JCVideoPlayer.setJcUserAction(new MyUserActionStandard());
    }



    public void getOk() {

        new Thread() {



            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(VideoBean_url).build();
                    Response respons = client.newCall(request).execute();
                    String string = respons.body().string();
                    LogUtils.MyLog("TAG", "---------" + string);

                    Gson gson = new Gson();
                    VoideBean voideBean = gson.fromJson(string, VoideBean.class);
                    video_url = voideBean.getVideo().getChapters3().get(0).getUrl();
                    video_url1 = voideBean.getVideo().getChapters2().get(0).getUrl();

                    LogUtils.MyLog("TAG", "sssssssssssssssssss——————" + video_url);

                    handler.sendEmptyMessage(HANDLERMSG);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }.start();

    }


    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void fX() {
        UMVideo video = new UMVideo(video_url);
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

    @Override
    public void sC() {

//        work = new Work();
//        work.setData(data);
//        work.setTitle(title);
//        work.setUrl(video_url);
//        work.setImage(image);
//        add(work);
//        selec();

    }

    @Override
    public void qxsc() {

//        delete(work);
//        selec();
    }

    @Override
    public void quit() {
       this.finish();
    }

    @Override
    public void gaoqin() {

        SPURL = video_url;
        handler.sendEmptyMessage(HANDLERMSG);
        Toast.makeText(BroadcastSpActivity.this, "切换成功", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void liuchang() {
        SPURL = video_url1;
        handler.sendEmptyMessage(HANDLERMSG);
        Toast.makeText(BroadcastSpActivity.this, "切换成功", Toast.LENGTH_SHORT).show();


    }

    class MyUserActionStandard implements JCUserActionStandard {

        @Override
        public void onEvent(int type, String url, int screen, Object... objects) {
            switch (type) {
                case JCUserAction.ON_CLICK_START_ICON:
                    Log.i("USER_EVENT", "ON_CLICK_START_ICON" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserAction.ON_CLICK_START_ERROR:
                    Log.i("USER_EVENT", "ON_CLICK_START_ERROR" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserAction.ON_CLICK_START_AUTO_COMPLETE:
                    Log.i("USER_EVENT", "ON_CLICK_START_AUTO_COMPLETE" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserAction.ON_CLICK_PAUSE:
                    Log.i("USER_EVENT", "ON_CLICK_PAUSE" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserAction.ON_CLICK_RESUME:
                    Log.i("USER_EVENT", "ON_CLICK_RESUME" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserAction.ON_SEEK_POSITION:
                    Log.i("USER_EVENT", "ON_SEEK_POSITION" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserAction.ON_AUTO_COMPLETE:
                    Log.i("USER_EVENT", "ON_AUTO_COMPLETE" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserAction.ON_ENTER_FULLSCREEN:
                    Log.i("USER_EVENT", "ON_ENTER_FULLSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserAction.ON_QUIT_FULLSCREEN:
                    Log.i("USER_EVENT", "ON_QUIT_FULLSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserAction.ON_ENTER_TINYSCREEN:
                    Log.i("USER_EVENT", "ON_ENTER_TINYSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserAction.ON_QUIT_TINYSCREEN:
                    Log.i("USER_EVENT", "ON_QUIT_TINYSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserAction.ON_TOUCH_SCREEN_SEEK_VOLUME:
                    Log.i("USER_EVENT", "ON_TOUCH_SCREEN_SEEK_VOLUME" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserAction.ON_TOUCH_SCREEN_SEEK_POSITION:
                    Log.i("USER_EVENT", "ON_TOUCH_SCREEN_SEEK_POSITION" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;

                case JCUserActionStandard.ON_CLICK_START_THUMB:
                    Log.i("USER_EVENT", "ON_CLICK_START_THUMB" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                case JCUserActionStandard.ON_CLICK_BLANK:
                    Log.i("USER_EVENT", "ON_CLICK_BLANK" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    break;
                default:
                    Log.i("USER_EVENT", "unknow");
                    break;
            }
        }
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

    public void selec() {

        DaoMaster master = new DaoMaster(MyHelper.gethelper(BroadcastSpActivity.this).getr());
        DaoSession daoSession = master.newSession();
        WorkDao workDao = daoSession.getWorkDao();

        QueryBuilder<Work> workQueryBuilder = workDao.queryBuilder();
        List<Work> list = workQueryBuilder.list();

        list.get(0).getUrl();

    }
}
