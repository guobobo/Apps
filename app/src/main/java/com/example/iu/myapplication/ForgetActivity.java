package com.example.iu.myapplication;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iu.myapplication.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class ForgetActivity extends BaseActivity {

    @Bind(R.id.forget_backImage)
    ImageView forgetBackImage;
    @Bind(R.id.forget_phoneNumber)
    EditText forgetPhoneNumber;
    @Bind(R.id.forget_Iamge_edit_Yanzhengma)
    EditText forgetIamgeEditYanzhengma;
    @Bind(R.id.forget_text_Yanzhengma)
    TextView forgetTextYanzhengma;
    @Bind(R.id.forget_duanxin_edit_Yanzhengma)
    EditText forgetDuanxinEditYanzhengma;
    @Bind(R.id.forget_btn_Yanzhengma)
    Button forgetBtnYanzhengma;
    @Bind(R.id.forget_password)
    EditText forgetPassword;
    @Bind(R.id.forget_btn_found)
    Button forgetBtnFound;

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget;
    }

    @Override
    public void initView() {

    }

    @OnClick(R.id.forget_backImage)
    public void onViewClicked() {

        finish();
    }
}
