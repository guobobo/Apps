package com.example.iu.myapplication.module.home.Ativity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.iu.myapplication.R;
import com.example.iu.myapplication.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InteractiveActivity extends BaseActivity {

    @Bind(R.id.interactive_recy)
    RecyclerView interactiveRecy;
    @Bind(R.id.activity_interactive)
    LinearLayout activityInteractive;

    @Override
    public int getLayoutId() {
        return R.layout.activity_interactive;
    }

    @Override
    public void initView() {
//        InteractiveAdapter interactiveAdapter = new InteractiveAdapter(this,);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.interactive_recy)
    public void onViewClicked() {
        Intent intent = new Intent(this,InteractivewebActivity.class);
        startActivity(intent);
    }
}
