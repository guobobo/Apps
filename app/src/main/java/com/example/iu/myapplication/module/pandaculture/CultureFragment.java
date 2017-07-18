package com.example.iu.myapplication.module.pandaculture;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidkun.PullToRefreshRecyclerView;
import com.example.iu.myapplication.R;
import com.example.iu.myapplication.base.BaseFragment;
import com.example.iu.myapplication.config.LogUtils;
import com.example.iu.myapplication.model.entity.CultureBean;
import com.example.iu.myapplication.module.pandabroadcast.activity.BroadcastSpActivity;
import com.example.iu.myapplication.module.pandaculture.adapter.CultureAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dell on 2017/7/12.
 */

public class CultureFragment extends BaseFragment implements CultureContarct.View,CultureAdapter.setListentoevents {
    PullToRefreshRecyclerView culturefragemntPrecy;
    @Bind(R.id.culture_xrecy)
    XRecyclerView cultureXrecy;

    CultureContarct.Presenter presenter;
    private List<CultureBean.BigImgBean> list1;
    private List<CultureBean.ListBean> list2;
    private List<CultureBean.BigImgBean> list11 = new ArrayList<>();
    private List<CultureBean.ListBean> list22 = new ArrayList<>();
    private XRecyclerView xRecyclerView;
    private CultureAdapter cultureAdapter;


    @Override
    public int getLayoutId() {
        return R.layout.culturefragment;
    }

    @Override
    public void initView(View view) {
        xRecyclerView = (XRecyclerView) view.findViewById(R.id.culture_xrecy);
        cultureAdapter = new CultureAdapter(list11,list22,getActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        xRecyclerView.setLayoutManager(linearLayoutManager);
        xRecyclerView.setAdapter(cultureAdapter);

        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xRecyclerView.refreshComplete();
                list22.clear();
                list22.addAll(list2);
                list11.clear();
                list11.addAll(list1);
                cultureAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLoadMore() {
                xRecyclerView.loadMoreComplete();
                Toast.makeText(getActivity(), "没有更多珊数据", Toast.LENGTH_SHORT).show();

            }

        });
        cultureAdapter.setInterfacecallback(this);



    }

    @Override
    public void loadDate() {

        presenter.start();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void setResult(CultureBean cultureBean) {


        list1 = cultureBean.getBigImg();
        list11.clear();
        list11.addAll(list1);
        list2 = cultureBean.getList();
        list22.clear();
        list22.addAll(list2);

    }


    @Override
    public void setMessage(String msg) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void setPresenter(CultureContarct.Presenter presenter) {

        this.presenter = presenter;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.culture_xrecy)
    public void onViewClicked() {
    }

    @Override
    public void Interfacecallback(int position) {
        Intent intent = new Intent(getActivity(), BroadcastSpActivity.class);
        CultureBean.ListBean listBean = list2.get(position-1);
        String title = listBean.getTitle();
        String url = "";
        intent.putExtra("title",title);
        LogUtils.MyLog("TAG","........................"+title);
        intent.putExtra("url",url);
        startActivity(intent);


    }
}
