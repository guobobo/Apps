package com.example.iu.myapplication.module.pandalive.wonderful;


import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.iu.myapplication.R;
import com.example.iu.myapplication.base.BaseFragment;
import com.example.iu.myapplication.model.entity.WonderfulBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;

public class WonderfulFragment extends BaseFragment implements WonderfulContarct.View {

    @Bind(R.id.wond_xrecy)
    XRecyclerView wondXrecy;
    private WonderfulContarct.Presenter presenter;

    private ArrayList<WonderfulBean.VideoBean> list;
    private WonderfulAdapter wonderfulAdapter;

    private static String VSID="VSET100167216881";
    private static final String n="7";
    private static final String serviceId="panda";
    private static final String o="desc";
    private static final String of="time";
    private static int p=1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_wonderful;
    }

    @Override
    public void initView(View view) {

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
                }, 2000);
                presenter.Addthenumber(VSID,n,serviceId,o,of,p+"");
                wondXrecy.loadMoreComplete();
                wonderfulAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void setMessage(String msg) {

    }
    public static void setVSID(String s){
        VSID=s;
    }
    public static String getVSID(){
        return VSID;
    }
}
