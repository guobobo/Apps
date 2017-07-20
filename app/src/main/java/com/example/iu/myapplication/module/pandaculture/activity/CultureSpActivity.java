package com.example.iu.myapplication.module.pandaculture.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.iu.myapplication.R;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class CultureSpActivity extends AppCompatActivity {

    private JCVideoPlayerStandard culture_jc;
    private String title;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture_sp);
//        title = getIntent().getStringExtra("title");
//        url = getIntent().getStringExtra("url");
//        initView();
    }

//    private void initView() {
//        culture_jc = (JCVideoPlayerStandard) findViewById(R.id.culture_jc);
//        culture_jc.setUp("http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4"
//                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL);
//        culture_jc.thumbImageView.setImageResource(R.drawable._no_img);
//        JCVideoPlayer.setJcUserAction(new MyUserActionStandard());
//
//        ImageView imageView = (ImageView) findViewById(R.id.culture_img1);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(CultureSpActivity.this, "333", Toast.LENGTH_SHORT).show();
//            }
//        });
//        TextView textView = (TextView) findViewById(R.id.culture_title);
//        textView.setText(title);
//    }

}

