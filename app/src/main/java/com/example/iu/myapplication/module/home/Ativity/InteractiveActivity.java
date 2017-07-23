package com.example.iu.myapplication.module.home.Ativity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.iu.myapplication.App;
import com.example.iu.myapplication.R;
import com.example.iu.myapplication.base.BaseActivity;
import com.example.iu.myapplication.config.UrlUtils;
import com.example.iu.myapplication.model.entity.InteractivesBean;
import com.example.iu.myapplication.module.home.adapter.InteractiveAdapter;
import com.example.iu.myapplication.module.pandabroadcast.activity.BroadcastWebActivity;
import com.example.iu.myapplication.net.OkhttpUtils;
import com.example.iu.myapplication.net.callback.MyNetWorkCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class InteractiveActivity extends BaseActivity implements InteractiveAdapter.MyOnClickListener{

    @Bind(R.id.interactive_recy)
    RecyclerView interactiveRecy;
private ArrayList<InteractivesBean.InteractiveBean> list = new ArrayList<InteractivesBean.InteractiveBean>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_interactive;
    }

    @Override
    public void initView() {

        OkhttpUtils.getInstance().get(UrlUtils.INTERACTIVE, null, new MyNetWorkCallBack<InteractivesBean>() {
            @Override
            public void onSuccess(InteractivesBean interactiveBean) {

                List<InteractivesBean.InteractiveBean> interactive = interactiveBean.getInteractive();

                list.addAll(interactive);

                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        InteractiveAdapter interactiveAdapter = new InteractiveAdapter(InteractiveActivity.this, list);

                        LinearLayoutManager manager = new LinearLayoutManager(InteractiveActivity.this);

                        interactiveRecy.setLayoutManager(manager);

                        interactiveRecy.setAdapter(interactiveAdapter);

                        interactiveAdapter.setListener(InteractiveActivity.this);
                    }
                });
            }

            @Override
            public void onError(String msg) {
                Toast.makeText(InteractiveActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void setOnClick(int pos) {

        Intent intent = new Intent(InteractiveActivity.this, BroadcastWebActivity.class);

        intent.putExtra("name",list.get(pos).getUrl());

        startActivity(intent);
    }
}
