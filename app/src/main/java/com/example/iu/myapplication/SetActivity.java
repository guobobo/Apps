package com.example.iu.myapplication;

import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.iu.myapplication.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class SetActivity extends BaseActivity {

    @Bind(R.id.login_backImage)
    ImageView loginBackImage;
    @Bind(R.id.set_checkMsg)
    CheckBox setCheckMsg;
    @Bind(R.id.relative_msg)
    RelativeLayout relativeMsg;
    @Bind(R.id.set_checkNext)
    CheckBox setCheckNext;
    @Bind(R.id.relative_next)
    RelativeLayout relativeNext;
    @Bind(R.id.relative_delete)
    RelativeLayout relativeDelete;
    @Bind(R.id.relative_feedback)
    RelativeLayout relativeFeedback;
    @Bind(R.id.relative_upgrade)
    RelativeLayout relativeUpgrade;
    @Bind(R.id.relative_like)
    RelativeLayout relativeLike;
    @Bind(R.id.relative_about)
    RelativeLayout relativeAbout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_set;
    }

    @Override
    public void initView() {

    }

    @OnClick(R.id.login_backImage)
    public void onViewClicked() {
        finish();
    }
}
