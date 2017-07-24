package com.example.iu.myapplication.module.chinalive.item;


import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.iu.myapplication.app.App;
import com.example.iu.myapplication.R;
import com.example.iu.myapplication.base.BaseFragment;
import com.example.iu.myapplication.model.entity.ChinaListBean;
import com.example.iu.myapplication.net.OkhttpUtils;
import com.example.iu.myapplication.net.callback.MyNetWorkCallBack;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;

import butterknife.Bind;

public class ChinaItemFragment extends BaseFragment implements ChinaItemContarct.View {

    @Bind(R.id.chinaitem_recy)
    XRecyclerView chinaitemRecy;
    private ChinaItemContarct.Presenter presenter;
    private ArrayList<ChinaListBean.LiveBean> tablistBeen = new ArrayList<>();
    ChinaItemAdapter chinaItemAdapter;
    String url;
    public ChinaItemFragment(String url) {
        this.url=url;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_china_item;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void loadDate() {
        presenter.start();
        inithttp();
    }
    private void inithttp() {
        OkhttpUtils.getInstance().get(url, null, new MyNetWorkCallBack<ChinaListBean>() {
            @Override
            public void onSuccess(ChinaListBean chinaListBean) {
                tablistBeen.clear();
                tablistBeen.addAll(chinaListBean.getLive());
                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        chinaitemRecy.setLayoutManager(manager);
                        chinaItemAdapter=new ChinaItemAdapter(getContext(),tablistBeen);
                        chinaitemRecy.setAdapter(chinaItemAdapter);
                        chinaitemRecy.setLoadingListener(new XRecyclerView.LoadingListener() {
                            @Override
                            public void onRefresh() {
                                chinaItemAdapter.notifyDataSetChanged();
                                chinaitemRecy.refreshComplete();
                            }

                            @Override
                            public void onLoadMore() {
                                chinaItemAdapter.notifyDataSetChanged();
                                chinaitemRecy.loadMoreComplete();
                            }
                        });
                    }
                });

            }

            @Override
            public void onError(String msg) {

            }
        });

    }
    @Override
    public void setPresenter(ChinaItemContarct.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setMessage(String msg) {

    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(getActivity());
        MobclickAgent.onPageStart("ChinaItemFragment");//统计时长
    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(getActivity());
        MobclickAgent.onPageStart("ChinaItemFragment");
    }

}
