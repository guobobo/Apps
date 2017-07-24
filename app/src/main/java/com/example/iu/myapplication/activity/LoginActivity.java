package com.example.iu.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iu.myapplication.R;
import com.example.iu.myapplication.base.BaseActivity;
import com.example.iu.myapplication.config.LogUtils;
import com.example.iu.myapplication.model.entity.LoginBean;
import com.example.iu.myapplication.model.entity.NiChengBean;
import com.google.gson.Gson;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
    private OkHttpClient client= new OkHttpClient();

    //登录成功获取用户昵称
    private final int MSG_LGOIN_IN_GET_NICKNAME = 101;
    //登录失败
    private final int MSG_LOGIN_IN_ERROR = 102;
    //获取昵称成功
    private static final int MSG_GET_NICKNAME_SUCCESS = 103;

    private static final int MSG_LOGIN_DISANFANG_SUCCESS = 104;

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){

                case MSG_LOGIN_IN_ERROR:

                    Toast.makeText(LoginActivity.this, "登录失败，您的账户不存在或无网络", Toast.LENGTH_SHORT).show();

                    break;


                case MSG_LGOIN_IN_GET_NICKNAME:

                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                    getUserTicket();
                    break;

                case MSG_GET_NICKNAME_SUCCESS:

                    SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);

                    SharedPreferences.Editor edit = login.edit();

                    edit.putString("mNickname",mNickname);

                    boolean commit = edit.commit();

                    if(commit){
                        Intent intent = new Intent(LoginActivity.this,PersonalActivity.class);

                        startActivity(intent);
                    }

                    break;

                case MSG_LOGIN_DISANFANG_SUCCESS:

                    SharedPreferences login1 = getSharedPreferences("login", MODE_PRIVATE);

                    SharedPreferences.Editor edit1 = login1.edit();

                    String substring = mNickname.substring(18);

                    edit1.putString("mNickname",substring);

                    boolean commit1 = edit1.commit();

                    if(commit1){
                        Intent intent = new Intent(LoginActivity.this,PersonalActivity.class);

                        startActivity(intent);
                    }

                    break;

            }

        }
    };
    private String mUserSeqId;
    private String verifycode;
    private String mNickname;

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

                        LogUtils.MyLog("TAG","用户名："+map.get("uid"));

                        mNickname = map.get("uid");

                        if(!TextUtils.isEmpty(mNickname)){
                            handler.sendEmptyMessage(MSG_LOGIN_DISANFANG_SUCCESS);
                        }
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
                        LogUtils.MyLog("TAG","用户名："+map.get("screen_name"));
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

                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWard)) {

                    nullText.setVisibility(View.VISIBLE);
                }

                getLogin();


                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

    //登录
    private void  getLogin(){

        String userName = loginCntvusername.getText().toString().trim();
        String passWard = loginCntvpassward.getText().toString().trim();

        //http://cbox_mobile.regclientuser.cntv.cn
        String from = "https://reg.cntv.cn/login/login.action";
        try {
            String url = from + "?username="
                    + URLEncoder.encode(userName, "UTF-8")
                    + "&password=" + passWard
                    + "&service=client_transaction" + "&from="
                    + URLEncoder.encode(from, "UTF-8");

            Request request = new Request.Builder().url(url)
                    .addHeader("Referer", URLEncoder.encode(from, "UTF-8"))
                    .addHeader("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CYNTV_MOBILE", "UTF-8"))
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    LogUtils.MyLog("TAG",e.getMessage().toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    String string = response.body().string();

                    LogUtils.MyLog("TAG","登录成功：："+string);

                    Gson gson = new Gson();

                    LoginBean loginBean = gson.fromJson(string, LoginBean.class);

                    String errType = loginBean.getErrType();

                    mUserSeqId = loginBean.getUser_seq_id();

                    Headers headers = response.headers();

                    verifycode = headers.get("Set-Cookie");

                    if(errType.equals("0")){
                        handler.sendEmptyMessage(MSG_LGOIN_IN_GET_NICKNAME);
                    }else {

                        String errMsg = loginBean.getErrMsg();

                        Message message = handler.obtainMessage();

                        message.obj = errMsg;
                        message.what = MSG_LOGIN_IN_ERROR;

                        handler.sendMessage(message);
                    }
                }
            });

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //获取昵称
    private void getUserTicket(){

        String form = "http://cbox_mobile.regclientuser.cntv.cn";
        String url = "http://my.cntv.cn/intf/napi/api.php" + "?client="
                + "cbox_mobile" + "&method=" + "user.getNickName"
                + "&userid=" + mUserSeqId;

//        httpGet.addHeader("Referer",
//                URLEncoder.encode(client, "UTF-8"));
//        httpGet.addHeader("User-Agent", URLEncoder.encode(
//                "CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"));
//        httpGet.addHeader("Cookie", "verifycode=" + verifycode);

        try {
            Request request = new Request.Builder()
                    .addHeader("Referer",URLEncoder.encode(form, "UTF-8"))
                    .addHeader("User-Agent",URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"))
                    .addHeader("Cookie",verifycode)
                    .url(url)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    LogUtils.MyLog("TAG",e.getMessage().toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    String string = response.body().string();

                    LogUtils.MyLog("TAG","昵称请Josn::"+string);

                    Gson gson = new Gson();

                    NiChengBean niChengBean = gson.fromJson(string, NiChengBean.class);

                    mNickname = niChengBean.getContent().getNickname();


                    handler.sendEmptyMessage(MSG_GET_NICKNAME_SUCCESS);
                }
            });

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
