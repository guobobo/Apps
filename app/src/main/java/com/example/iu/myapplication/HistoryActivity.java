package com.example.iu.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.iu.myapplication.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class HistoryActivity extends BaseActivity {


    @Bind(R.id.history_backImage)
    ImageView historyBackImage;
    @Bind(R.id.history_editBtn)
    Button historyEditBtn;
    @Bind(R.id.history_listview)
    ListView historyListview;
    @Bind(R.id.history_item_chooseBtn)
    Button historyItemChooseBtn;
    @Bind(R.id.history_item_deleteBtn)
    Button historyItemDeleteBtn;
    @Bind(R.id.liner_chooseAnddelete)
    LinearLayout linerChooseAnddelete;

    @Override
    public int getLayoutId() {
        return R.layout.activity_history;
    }

    @Override
    public void initView() {

    }

    @OnClick({R.id.history_backImage, R.id.history_editBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.history_backImage:
                finish();
                break;
            case R.id.history_editBtn:
                break;
        }
    }
}
