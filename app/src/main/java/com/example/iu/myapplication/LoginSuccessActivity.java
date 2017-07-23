package com.example.iu.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iu.myapplication.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginSuccessActivity extends BaseActivity {


    @Bind(R.id.login_success_backImage)
    ImageView loginSuccessBackImage;
    @Bind(R.id.avatarImage_loginsuccess)
    ImageView avatarImageLoginsuccess;
    @Bind(R.id.linear_loginsuccess_tuoxiang)
    LinearLayout linearLoginsuccessTuoxiang;
    @Bind(R.id.mNickname_loginsuccess)
    TextView mNicknameLoginsuccess;
    @Bind(R.id.relative_loginsuccess_nicheng)
    RelativeLayout relativeLoginsuccessNicheng;
    @Bind(R.id.Btn_loginsuccess)
    Button BtnLoginsuccess;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login_success;
    }

    @Override
    public void initView() {

        Intent intent = getIntent();

        String mNickname = intent.getStringExtra("mNickname");

        mNicknameLoginsuccess.setText(mNickname);
    }

    @OnClick({R.id.login_success_backImage, R.id.linear_loginsuccess_tuoxiang, R.id.relative_loginsuccess_nicheng, R.id.Btn_loginsuccess})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_success_backImage:
                finish();
                break;
            case R.id.linear_loginsuccess_tuoxiang:
                break;
            case R.id.relative_loginsuccess_nicheng:
                break;
            case R.id.Btn_loginsuccess:

                SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);

                SharedPreferences.Editor edit = login.edit();

                edit.clear();

                boolean commit = edit.commit();

                if(commit) {
                    Toast.makeText(LoginSuccessActivity.this, "退出成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginSuccessActivity.this,PersonalActivity.class));
                    finish();
                }

                break;
        }
    }
}
