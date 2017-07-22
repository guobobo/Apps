package com.example.iu.myapplication.module.pandaculture.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.iu.myapplication.R;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class CultureSpActivity extends AppCompatActivity {

    private JCVideoPlayerStandard JCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture_sp2);
        initView();
    }

    private void initView() {
        JCV = (JCVideoPlayerStandard) findViewById(R.id.JCV);
    }


}
