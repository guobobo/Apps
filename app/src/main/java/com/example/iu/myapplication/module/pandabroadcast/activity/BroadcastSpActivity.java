package com.example.iu.myapplication.module.pandabroadcast.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iu.myapplication.R;

import fm.jiecao.jcvideoplayer_lib.JCUserAction;
import fm.jiecao.jcvideoplayer_lib.JCUserActionStandard;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

import static android.view.View.VISIBLE;

public class BroadcastSpActivity extends AppCompatActivity {

    private JCVideoPlayerStandard broadcast_jc;
    private String title;
    private TextView textView,ooo;
    private RelativeLayout broadcastsp_rl;
    private ImageView img1;
    private ImageView img2;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_sp);
//        BroadCastListBean.ListBean bean = getIntent().getParcelableExtra("name");
//        title = bean.getTitle();

        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        initView();
    }

    private void initView() {
        broadcast_jc = (JCVideoPlayerStandard) findViewById(R.id.broadcast_jc);
        broadcast_jc.setUp("http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL);
        broadcast_jc.thumbImageView.setImageResource(R.drawable._no_img);
        JCVideoPlayer.setJcUserAction(new MyUserActionStandard());
        textView = (TextView) findViewById(R.id.broadcastSp_title);
        broadcastsp_rl = (RelativeLayout) findViewById(R.id.Broadcastsp_rl);
        img1 = (ImageView) findViewById(R.id.broadcastSp_img1);
        img2 = (ImageView) findViewById(R.id.broadcastSp_img2);
        textView.setText(title);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BroadcastSpActivity.this, "555", Toast.LENGTH_SHORT).show();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BroadcastSpActivity.this, "777", Toast.LENGTH_SHORT).show();
                int id = img2.getId();
                int imageAlpha = img2.getImageAlpha();
                if(imageAlpha == R.drawable.play_fullsrcee_collect_true) {
                    img2.setImageResource(R.drawable.play_fullsrcee_collect);
                }else{
                    img2.setImageResource(R.drawable.play_fullsrcee_collect_true);
                }

            }
        });



        broadcast_jc.thumbImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int visibility = broadcastsp_rl.getVisibility();
                if(visibility == View.GONE) {
                    broadcastsp_rl.setVisibility(VISIBLE);
                }else if(visibility == VISIBLE) {
                    broadcastsp_rl.setVisibility(View.GONE);
                }
            }
        });


        broadcast_jc.tinyBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BroadcastSpActivity.this, "888", Toast.LENGTH_SHORT).show();
            }
        });



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
