package com.example.iu.myapplication.module.pandalive.wonderful;


import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ProgressBar;

import com.example.iu.myapplication.R;
import com.example.iu.myapplication.base.BaseFragment;
import com.example.iu.myapplication.customize.HistoryUtils;
import com.example.iu.myapplication.model.entity.WonderfulBean;
import com.example.iu.myapplication.module.pandabroadcast.activity.BroadcastSpActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;

import butterknife.Bind;

public class WonderfulFragment extends BaseFragment implements WonderfulContarct.View {

    @Bind(R.id.wond_xrecy)
    XRecyclerView wondXrecy;
    private WonderfulContarct.Presenter presenter;

    private ArrayList<WonderfulBean.VideoBean> list;
    private WonderfulAdapter wonderfulAdapter;

    private  String VSID;
    private static String n="7";
    private static final String serviceId="panda";
    private static final String o="desc";
    private static final String of="time";
    private static int p=1;
    private ProgressBar wonderful;

    public WonderfulFragment(String VSID) {
        this.VSID = VSID;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_wonderful;
    }

    @Override
    public void initView(View view) {
        wonderful = (ProgressBar) view.findViewById(R.id.wonderful_progress);

    }

    @Override
    public void loadDate() {
        presenter.start();
        presenter.Addthenumber(VSID,n,serviceId,o,of,p+"");
    }

    @Override
    public void setPresenter(WonderfulContarct.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setResult(WonderfulBean wonderfulBean) {

        if(wonderfulBean!=null) {
            wonderful.setVisibility(View.GONE);
        }

        list=new ArrayList<>();
        for (int i=0;i<wonderfulBean.getVideo().size();i++){
            WonderfulBean.VideoBean videoBean = wonderfulBean.getVideo().get(i);
            list.add(videoBean);
        }
        wonderfulAdapter=new WonderfulAdapter(getContext(),list);

        wondXrecy.setLayoutManager(new LinearLayoutManager(getContext()));
        wondXrecy.setAdapter(wonderfulAdapter);
        wondXrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                p=1;
                presenter.Addthenumber(VSID,n,serviceId,o,of,p+"");
                wondXrecy.refreshComplete();
                wonderfulAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        p++;
                    }
                }, 1000);
                presenter.Addthenumber(VSID,n,serviceId,o,of,p+"");
                wondXrecy.loadMoreComplete();
                wonderfulAdapter.notifyDataSetChanged();
            }
        });

        wonderfulAdapter.setOnWonderful(new WonderfulAdapter.OnWonderful() {
            @Override
            public void onWonderful(int position) {
                Intent intent = new Intent(getActivity(), BroadcastSpActivity.class);
                WonderfulBean.VideoBean videoBean = list.get(position - 1);

                String title = videoBean.getT();
                String image = videoBean.getImg();
                String videoLength = videoBean.getLen();
                String id = videoBean.getVid();

                intent.putExtra("title",title);
                intent.putExtra("image",image);
                intent.putExtra("duration",videoLength);
                intent.putExtra("id",id);

                startActivity(intent);
                HistoryUtils.getInstance(getActivity()).instert(title, image, videoLength);
            }
        });

    }

    @Override
    public void setMessage(String msg) {

    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(getActivity());
        MobclickAgent.onPageStart("WonderfulFragment");//统计时长
    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(getActivity());
        MobclickAgent.onPageStart("WonderfulFragment");
    }

}
