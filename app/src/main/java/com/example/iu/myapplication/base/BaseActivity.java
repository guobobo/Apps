package com.example.iu.myapplication.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.iu.myapplication.app.App;

import butterknife.ButterKnife;

//activity基类
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.context = this;
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
    }

    //初始化布局..
    public abstract int getLayoutId();

    //初始化数据
    public abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
