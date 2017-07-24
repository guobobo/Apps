package com.example.iu.myapplication.activity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.iu.myapplication.R;
import com.example.iu.myapplication.base.BaseActivity;
import com.umeng.message.IUmengCallback;
import com.umeng.message.PushAgent;

import butterknife.Bind;
import butterknife.OnClick;

import static com.example.iu.myapplication.R.id.login_backImage;

public class SetActivity extends BaseActivity {

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
    @Bind(login_backImage)
    ImageView loginBackImage;
    @Bind(R.id.set_checkMsg)
    CheckBox setCheckMsg;
    @Bind(R.id.activity_set)
    LinearLayout activitySet;

    @Override
    public int getLayoutId() {
        return R.layout.activity_set;
    }

    @Override
    public void initView() {

    }



    @OnClick({login_backImage, R.id.set_checkMsg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case login_backImage:
                finish();
                break;
            case R.id.set_checkMsg:

                PushAgent mPushAgent = PushAgent.getInstance(this);
                if(setCheckMsg.isChecked() == false) {

                    mPushAgent.disable(new IUmengCallback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onFailure(String s, String s1) {

                        }
                    });

                    Toast.makeText(SetActivity.this, "你已经关闭了消息推送！", Toast.LENGTH_SHORT).show();
                }else {
                    mPushAgent.enable(new IUmengCallback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onFailure(String s, String s1) {

                        }

                    });
                    Toast.makeText(SetActivity.this, "你已经开启了消息推送！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
