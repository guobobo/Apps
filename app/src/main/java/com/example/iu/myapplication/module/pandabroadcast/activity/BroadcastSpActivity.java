package com.example.iu.myapplication.module.pandabroadcast.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.iu.myapplication.R;
import com.example.iu.myapplication.model.entity.BroadCastListBean;

import fm.jiecao.jcvideoplayer_lib.JCUserAction;
import fm.jiecao.jcvideoplayer_lib.JCUserActionStandard;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class BroadcastSpActivity extends AppCompatActivity {

    private JCVideoPlayerStandard broadcast_jc;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_sp);
        BroadCastListBean.ListBean bean = getIntent().getParcelableExtra("name");
        title = bean.getTitle();
        initView();
    }

    private void initView() {
        broadcast_jc = (JCVideoPlayerStandard) findViewById(R.id.broadcast_jc);
        broadcast_jc.setUp("http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, title);
        Glide.with(this)
                .load("http://img4.jiecaojingxuan.com/2016/11/23/00b026e7-b830-4994-bc87-38f4033806a6.jpg@!640_360")
                .into(broadcast_jc.thumbImageView);
        broadcast_jc.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BroadcastSpActivity.this, "666", Toast.LENGTH_SHORT).show();
            }
        });


        JCVideoPlayer.setJcUserAction(new MyUserActionStandard());
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
    }
