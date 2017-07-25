package com.example.iu.myapplication.fargment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iu.myapplication.R;
import com.example.iu.myapplication.config.LogUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by dell on 2017/7/19.
 */

public class PhoneFragment extends Fragment implements View.OnFocusChangeListener {

    @Bind(R.id.phonefragmentName)
    EditText phonefragmentName;
    @Bind(R.id.phonefragment_text_Yanzhengma)
    TextView phonefragmentTextYanzhengma;
    @Bind(R.id.phonefragment_duanxin_edit_Yanzhengma)
    EditText phonefragmentDuanxinEditYanzhengma;
    @Bind(R.id.phonefragment_btn_Yanzhengma)
    Button phonefragmentBtnYanzhengma;
    @Bind(R.id.phonefragmentpassward)
    EditText phonefragmentpassward;
    @Bind(R.id.phonefragment_check)
    CheckBox phonefragmentCheck;
    @Bind(R.id.phonefragment_btn_registered)
    Button phonefragmentBtnRegistered;
    @Bind(R.id.hint_phone)
    TextView hintPhone;
    @Bind(R.id.hint_password)
    TextView hintPassword;
    @Bind(R.id.hint_imagyanzhengma)
    TextView hintImagyanzhengma;
    @Bind(R.id.hint_duanxin)
    TextView hintDuanxin;
    @Bind(R.id.phonefragment_net_yanzhengma)
    ImageView phonefragmentNetYanzhengma;
    @Bind(R.id.phonefragment_Iamge_edit_Yanzhengma)
    EditText phonefragmentIamgeEditYanzhengma;
    private String mCaptchaEditTextString;

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
    private   OkHttpClient client = new OkHttpClient();
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case IMG_GET_SUCCES:

                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

                    Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);

                    phonefragmentNetYanzhengma.setImageBitmap(bitmap);
                    break;
                case IMG_GET_ERROR:


                    break;
                case MSG_LOGIN_SUCCESS:

                    Toast toast = Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();

                    break;
                case MSG_LOGIN_ERROR:
                    Toast toast1 = Toast.makeText(getActivity(), "注册失败", Toast.LENGTH_SHORT);
                    toast1.setGravity(Gravity.CENTER,0,0);
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
    private String jsonId;
    private byte[] bytes;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.registered_phonefragment, null);
        ButterKnife.bind(this, view);
        initView(view);

        return view;
    }

    private void initView(View view) {


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    //检查手机号
    private boolean checkPhone() {
        String phoneString = phonefragmentName.getText().toString().trim();
        if (TextUtils.isEmpty(phoneString)) {
            hintPhone.setText("手机号码不能为空");
            return false;
        }
        Pattern pattern = Pattern.compile("^1[3578]\\d{9}$");
        Matcher matcher = pattern.matcher(phoneString);
        if (matcher.matches()) {
            hintPhone.setText("");
            return true;
        } else {
            hintPhone.setText("手机格式不正确");
            return false;
        }
    }

    //检查密码
    private boolean checkPasswork() {
        String editpasswordsString = phonefragmentpassward.getText().toString();

        if (TextUtils.isEmpty(editpasswordsString)) {
            hintPassword.setText("密码不能为空");
            return false;
        } else if (editpasswordsString.length() < 6 || editpasswordsString.length() > 16) {
            hintPassword.setText("密码仅限6-16个字符");
            return false;
        } else {
            hintPassword.setText("");
            return true;
        }
    }

    /**
     * 检查手机验证码
     */

    private boolean checkPhoneCheck() {
        String phonecheck = phonefragmentDuanxinEditYanzhengma.getText().toString().trim();

        if (TextUtils.isEmpty(phonecheck)) {
            hintDuanxin.setText("验证码不能为空");
            return false;
        } else {
            hintDuanxin.setText(" ");
            return true;
        }
    }

    //图片验证码
    private boolean checkCaptcha() {

        mCaptchaEditTextString = phonefragmentIamgeEditYanzhengma.getText().toString().trim();
        if (mCaptchaEditTextString.contains(" ")) {
            hintImagyanzhengma.setText("验证码不正确");
            return false;
        }
        if (mCaptchaEditTextString == null || "".equals(mCaptchaEditTextString)) {
            hintImagyanzhengma.setText("验证码不能为空");
            return false;
        } else {
            hintImagyanzhengma.setText("");
            return true;
        }
    }


    @OnClick({R.id.phonefragment_btn_Yanzhengma, R.id.phonefragment_btn_registered, R.id.phonefragment_text_Yanzhengma})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.phonefragment_btn_Yanzhengma:

                //手机号是否输入正确
                boolean isPhone = checkPhone();
                if (isPhone) {

                    String url = "http://reg.cntv.cn/regist/getVerifiCode.action";
                    String from = "http://cbox_mobile.regclientuser.cntv.cn";
//                    手机号
                    String tPhoneNumber = phonefragmentName.getText().toString().trim();
//                    雁阵吗
                    String imgyanzhengma = phonefragmentIamgeEditYanzhengma.getText().toString().trim();

                    RequestBody body = new FormBody.Builder()
                            .add("method","getRequestVerifiCodeM")
                            .add("mobile",tPhoneNumber)
                            .add("verfiCodeType","1")
                            .add("verificationCode",imgyanzhengma)
                            .build();

                    try {
                        Request request = new Request.Builder().url(url)
                                .addHeader("Referer", URLEncoder.encode(from, "UTF-8"))
                                .addHeader("User-Agent",URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"))
                                .addHeader("Cookie",jsonId)
                                .post(body).build();

                        client.newCall(request).enqueue(new Callback() {
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
                    hintPhone.setText("手机号有误");
                }

                break;
            case R.id.phonefragment_btn_registered:

                    String url = "https://reg.cntv.cn/regist/mobileRegist.do";
                    String number = phonefragmentName.getText().toString().trim();
                    String tCheckPhone = phonefragmentDuanxinEditYanzhengma.getText().toString().trim();
                    String tPassWord = phonefragmentpassward.getText().toString();

                    try {
                        RequestBody body = new FormBody.Builder()
                                .add("method","saveMobileRegisterM")
                                .add("mobile",number)
                                .add("verfiCode",tCheckPhone)
                                .add("passWd",URLEncoder.encode(tPassWord, "UTF-8"))
                                .add("verfiCodeType","1")
                                .add("addons", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"))
                                .build();

                        Request request = new Request.Builder()
                                .url(url)
                                .addHeader("Referer", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"))
                                .addHeader("User-Agent",URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"))
                                .post(body)
                                .build();


                        client.newCall(request).enqueue(new Callback() {
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

            case R.id.phonefragment_text_Yanzhengma:
//                Toast.makeText(getActivity(), "正在获取验证码", Toast.LENGTH_SHORT).show();
                phonefragmentTextYanzhengma.setVisibility(View.GONE);
                phonefragmentNetYanzhengma.setVisibility(View.VISIBLE);

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
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }

    //判断是否有网
    public boolean isConnected() {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        try {
            ConnectivityManager connectivity = (ConnectivityManager) getActivity()
                    .getSystemService(getActivity().CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                // 获取网络连接管理的对象
                NetworkInfo info = connectivity.getActiveNetworkInfo();

                if (info != null && info.isConnected()) {
                    // 判断当前网络是否已经连接
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
        }
        return false;
    }


}