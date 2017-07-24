package com.example.iu.myapplication.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iu.myapplication.R;
import com.example.iu.myapplication.base.BaseActivity;
import com.example.iu.myapplication.config.LogUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ForgetActivity extends BaseActivity {


    //获取验证码图片成功、失败
    private static final int IMG_GET_SUCCES = 101;
    private static final int IMG_GET_ERROR = 102;

    //获取手机验证码成功、失败
    private static final int MSG_GETTING_SUCCESS = 103;
    private static final int MSG_GETTING_ERROR = 104;
    //手机号注册成功、失败
    private static final int MSG_LOGIN_SUCCESS = 105;
    private static final int MSG_LOGIN_ERROR = 106;
    //获取用户ID、昵称
    private static final int MSG_LGOIN_IN_GET_NICKNAME = 107;
    private static final int MSG_LOGIN_IN_ERROR = 108;
    private static final int MSG_GET_NICKNAME_SUCCESS = 109;
    @Bind(R.id.forget_backImage)
    ImageView forgetBackImage;
    @Bind(R.id.forget_phoneNumber)
    EditText forgetPhoneNumber;
    @Bind(R.id.forget_Iamge_edit_Yanzhengma)
    EditText forgetIamgeEditYanzhengma;
    @Bind(R.id.forget_Yanzhengma_t)
    TextView forgetYanzhengmaT;
    @Bind(R.id.forget_yanzhengma_i)
    ImageView forgetYanzhengmaI;
    @Bind(R.id.forget_duanxin_edit_Yanzhengma)
    EditText forgetDuanxinEditYanzhengma;
    @Bind(R.id.forget_btn_Yanzhengma)
    Button forgetBtnYanzhengma;
    @Bind(R.id.forget_password)
    EditText forgetPassword;
    @Bind(R.id.forget_btn_found)
    Button forgetBtnFound;
    private OkHttpClient client = new OkHttpClient();
    private String jsonId;
    private byte[] bytes;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case IMG_GET_SUCCES:

                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

                    Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);

                    forgetYanzhengmaI.setImageBitmap(bitmap);
                    break;
                case IMG_GET_ERROR:


                    break;
                case MSG_LOGIN_SUCCESS:

                    Toast toast = Toast.makeText(ForgetActivity.this, "修改成功", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                    break;
                case MSG_LOGIN_ERROR:
                    Toast toast1 = Toast.makeText(ForgetActivity.this, "修改失败", Toast.LENGTH_SHORT);
                    toast1.setGravity(Gravity.CENTER, 0, 0);
                    toast1.show();
                    break;
                case MSG_GETTING_SUCCESS:

                    break;
                case MSG_GETTING_ERROR:

                    break;
                case MSG_LGOIN_IN_GET_NICKNAME:

                    break;
                case MSG_LOGIN_IN_ERROR:

                    break;
                case MSG_GET_NICKNAME_SUCCESS:

                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget;
    }

    @Override
    public void initView() {

    }


    @OnClick({R.id.forget_backImage, R.id.forget_Yanzhengma_t, R.id.forget_btn_Yanzhengma, R.id.forget_btn_found})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.forget_backImage:
                finish();
                break;
            case R.id.forget_Yanzhengma_t:
                forgetYanzhengmaT.setVisibility(View.GONE);
                forgetYanzhengmaI.setVisibility(View.VISIBLE);

                Request request = new Request.Builder().url("http://reg.cntv.cn/simple/verificationCode.action").build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        Headers headers = response.headers();

                        jsonId = headers.get("Set-Cookie");

                        LogUtils.MyLog("TAG", "name::" + jsonId);

                        bytes = response.body().bytes();

                        mHandler.sendEmptyMessage(IMG_GET_SUCCES);
                    }
                });
                break;
            case R.id.forget_btn_Yanzhengma:

                //手机号是否输入正确
                boolean isPhone = checkPhone();
                if (isPhone) {

                    String url = "http://reg.cntv.cn/regist/getVerifiCode.action";
                    String from = "http://cbox_mobile.regclientuser.cntv.cn";
//                    手机号
                    String tPhoneNumber = forgetPhoneNumber.getText().toString().trim();
//                    雁阵吗
                    String imgyanzhengma = forgetIamgeEditYanzhengma.getText().toString().trim();

                    RequestBody body = new FormBody.Builder()
                            .add("method","getRequestVerifiCodeM")
                            .add("mobile",tPhoneNumber)
                            .add("verfiCodeType","1")
                            .add("verificationCode",imgyanzhengma)
                            .build();

                    try {
                        Request request1 = new Request.Builder().url(url)
                                .addHeader("Referer", URLEncoder.encode(from, "UTF-8"))
                                .addHeader("User-Agent",URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"))
                                .addHeader("Cookie",jsonId)
                                .post(body).build();

                        client.newCall(request1).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                LogUtils.MyLog("TAG",e.getMessage().toString());
                            }
                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                                String string = response.body().string();

                                LogUtils.MyLog("TAG","手机验证码打印："+string);

                                if(string.equals("success")){
                                    mHandler.sendEmptyMessage(MSG_GETTING_SUCCESS);
                                }
                            }
                        });
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                }else {
//                    hintPhone.setText("手机号有误");
                }

                break;
            case R.id.forget_btn_found:

                String url = "https://reg.cntv.cn/regist/mobileRegist.do";
                String number = forgetPhoneNumber.getText().toString().trim();
                String tCheckPhone = forgetDuanxinEditYanzhengma.getText().toString().trim();
                String tPassWord = forgetPassword.getText().toString();

                try {
                    RequestBody body = new FormBody.Builder()
                            .add("method","saveMobileRegisterM")
                            .add("mobile",number)
                            .add("verfiCode",tCheckPhone)
                            .add("passWd",URLEncoder.encode(tPassWord, "UTF-8"))
                            .add("verfiCodeType","1")
                            .add("addons", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"))
                            .build();

                    Request request2 = new Request.Builder()
                            .url(url)
                            .addHeader("Referer", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"))
                            .addHeader("User-Agent",URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"))
                            .post(body)
                            .build();


                    client.newCall(request2).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            LogUtils.MyLog("TAG",e.getMessage().toString());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            String loginSate = response.body().string();

                            LogUtils.MyLog("TAG","注册状态：："+loginSate);

                            if(loginSate.equals("success")){

                                mHandler.sendEmptyMessage(MSG_LOGIN_SUCCESS);
                            }else {
                                mHandler.sendEmptyMessage(MSG_LOGIN_ERROR);
                            }
                        }
                    });

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


                break;
        }
    }


    //检查手机号
    private boolean checkPhone() {
        String phoneString = forgetPhoneNumber.getText().toString().trim();
        if (TextUtils.isEmpty(phoneString)) {
//            hintPhone.setText("手机号码不能为空");
            return false;
        }
        Pattern pattern = Pattern.compile("^1[3578]\\d{9}$");
        Matcher matcher = pattern.matcher(phoneString);
        if (matcher.matches()) {
//            hintPhone.setText("");
            return true;
        } else {
//            hintPhone.setText("手机格式不正确");
            return false;
        }
    }

}
