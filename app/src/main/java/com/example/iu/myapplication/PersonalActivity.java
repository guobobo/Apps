package com.example.iu.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.iu.myapplication.base.BaseActivity;
import com.example.iu.myapplication.config.LogUtils;

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
    @Bind(R.id.personal_avatarImage)
    ImageView personalAvatarImage;
    @Bind(R.id.personal_avatarImage_success)
    ImageView personalAvatarImageSuccess;
    @Bind(R.id.personal_avatarusername_success)
    TextView personalAvatarusernameSuccess;
    @Bind(R.id.liner_login_success)
    LinearLayout linerLoginSuccess;

    @Override
    public int getLayoutId() {
        return R.layout.activity_personal;
    }

    @Override
    public void initView() {

        SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
        final String mNickname = login.getString("mNickname", "");
        LogUtils.MyLog("TAG","登录成功打印SharedPreferences：："+login);

        if (!mNickname.equals("")) {

            LogUtils.MyLog("TAG","登陆成功获取的昵称:::"+mNickname);

            linerLogin.setVisibility(View.GONE);

            linerLoginSuccess.setVisibility(View.VISIBLE);
            personalAvatarusernameSuccess.setText(mNickname);

            linerLoginSuccess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(PersonalActivity.this, LoginSuccessActivity.class);

                    intent.putExtra("mNickname",mNickname);

                    startActivity(intent);
                }
            });

        }

    }

    @OnClick({R.id.personal_backImage, R.id.liner_login, R.id.liner_history, R.id.liner_collection, R.id.linear_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personal_backImage:
                finish();
                break;
            case R.id.liner_login:
                startActivity(new Intent(PersonalActivity.this, LoginActivity.class));
                break;
            case R.id.liner_history:
                startActivity(new Intent(PersonalActivity.this, HistoryActivity.class));
                break;
            case R.id.liner_collection:
                startActivity(new Intent(PersonalActivity.this, CollectionActivity.class));
                break;
            case R.id.linear_set:
                startActivity(new Intent(PersonalActivity.this, SetActivity.class));
                break;
        }
    }
}
