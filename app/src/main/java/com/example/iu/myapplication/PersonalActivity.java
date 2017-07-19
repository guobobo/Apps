package com.example.iu.myapplication;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.iu.myapplication.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class PersonalActivity extends BaseActivity {

    @Bind(R.id.personal_backImage)
    ImageView personalBackImage;
    @Bind(R.id.liner_login)
    LinearLayout linerLogin;
    @Bind(R.id.liner_history)
    LinearLayout linerHistory;
    @Bind(R.id.liner_collection)
    LinearLayout linerCollection;
    @Bind(R.id.linear_set)
    LinearLayout linearSet;

    @Override
    public int getLayoutId() {
        return R.layout.activity_personal;
    }

    @Override
    public void initView() {
    }

    @OnClick({R.id.personal_backImage, R.id.liner_login, R.id.liner_history, R.id.liner_collection, R.id.linear_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personal_backImage:
                finish();
                break;
            case R.id.liner_login:
                startActivity(new Intent(PersonalActivity.this,LoginActivity.class));
                break;
            case R.id.liner_history:
                startActivity(new Intent(PersonalActivity.this,HistoryActivity.class));
                break;
            case R.id.liner_collection:
                startActivity(new Intent(PersonalActivity.this,CollectionActivity.class));
                break;
            case R.id.linear_set:
                startActivity(new Intent(PersonalActivity.this,SetActivity.class));
                break;
        }
    }
}
