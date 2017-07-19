package com.example.iu.myapplication;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iu.myapplication.base.BaseActivity;
import com.example.iu.myapplication.config.LogUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.login_backImage)
    ImageView loginBackImage;
    @Bind(R.id.login_registeredText)
    TextView loginRegisteredText;
    @Bind(R.id.linear_weixin)
    LinearLayout linearWeixin;
    @Bind(R.id.linear_qq)
    LinearLayout linearQq;
    @Bind(R.id.linear_weibo)
    LinearLayout linearWeibo;
    @Bind(R.id.login_cntvusername)
    EditText loginCntvusername;
    @Bind(R.id.login_cntvpassward)
    EditText loginCntvpassward;
    @Bind(R.id.login_forgetText)
    TextView loginForgetText;
    @Bind(R.id.login_loginBtn)
    Button loginLoginBtn;
    @Bind(R.id.null_text)
    TextView nullText;
    @Bind(R.id.activity_login)
    LinearLayout activityLogin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }

    @OnClick({R.id.login_backImage, R.id.login_registeredText, R.id.linear_weixin, R.id.linear_qq, R.id.linear_weibo, R.id.login_forgetText, R.id.login_loginBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_backImage:
                finish();
                break;
            case R.id.login_registeredText:
                startActivity(new Intent(LoginActivity.this, RegisteredActivity.class));
                break;
            case R.id.linear_weixin:
                Toast.makeText(LoginActivity.this, "点击QQ", Toast.LENGTH_SHORT).show();
                UMShareAPI.get(LoginActivity.this).doOauthVerify(LoginActivity.this, SHARE_MEDIA.WEIXIN, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        LogUtils.MyLog("TAG", "onStart");
                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        LogUtils.MyLog("TAG", "onComplete");
//                        map.get("");
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                        LogUtils.MyLog("TAG", "onError");
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {
                        LogUtils.MyLog("TAG", "onCancel");
                    }
                });

                break;
            case R.id.linear_qq:
                Toast.makeText(LoginActivity.this, "点击QQ", Toast.LENGTH_SHORT).show();
                UMShareAPI.get(LoginActivity.this).doOauthVerify(LoginActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        LogUtils.MyLog("TAG", "onStart");
                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        LogUtils.MyLog("TAG", "onComplete");
//                        map.get("");
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                        LogUtils.MyLog("TAG", "onError");
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {
                        LogUtils.MyLog("TAG", "onCancel");
                    }
                });

                break;
            case R.id.linear_weibo:

                UMShareAPI.get(LoginActivity.this).doOauthVerify(LoginActivity.this, SHARE_MEDIA.SINA, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        LogUtils.MyLog("TAG", "onStart");
                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        LogUtils.MyLog("TAG", "onComplete");
//                        map.get("");
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                        LogUtils.MyLog("TAG", "onError");
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {
                        LogUtils.MyLog("TAG", "onCancel");
                    }
                });
                break;
            case R.id.login_forgetText:
                startActivity(new Intent(LoginActivity.this,ForgetActivity.class));
                break;
            case R.id.login_loginBtn:

                String userName = loginCntvusername.getText().toString().trim();
                String passWard = loginCntvpassward.getText().toString().trim();

                if (userName.equals("") || passWard.equals("")) {

                    nullText.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}
