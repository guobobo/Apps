package com.example.iu.myapplication.module.pandabroadcast;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.iu.myapplication.App;
import com.example.iu.myapplication.R;
import com.example.iu.myapplication.base.BaseFragment;
import com.example.iu.myapplication.model.entity.BroadCastBean;
import com.example.iu.myapplication.model.entity.BroadCastListBean;
import com.example.iu.myapplication.module.pandabroadcast.activity.BroadcastSpActivity;
import com.example.iu.myapplication.module.pandabroadcast.activity.BroadcastWebActivity;
import com.example.iu.myapplication.module.pandabroadcast.adapter.BroadcastAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/7/12.
 */

public class BroadcastFragment extends BaseFragment implements BroadcastContarct.View,BroadcastAdapter.setListentoevents {


    BroadcastContarct.Presenter presenter;
    private XRecyclerView xRecyclerView;
    private List<BroadCastBean.DataBean.BigImgBean> list2;
    private List<BroadCastListBean.ListBean> list1;
    private List<BroadCastBean.DataBean.BigImgBean> list22 = new ArrayList<>();
    private List<BroadCastListBean.ListBean> list11 = new ArrayList<>();
    private BroadcastAdapter broadcastAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_pandabroadcast;
    }

    @Override
    public void initView(View view) {
        xRecyclerView = (XRecyclerView) view.findViewById(R.id.broadcast_xrecy);
        broadcastAdapter = new BroadcastAdapter(list22,list11,getActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        xRecyclerView.setLayoutManager(linearLayoutManager);
        xRecyclerView.setAdapter(broadcastAdapter);

        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xRecyclerView.refreshComplete();
                list22.clear();
                list22.addAll(list2);
                list11.clear();
                list11.addAll(list1);
                broadcastAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLoadMore() {
                xRecyclerView.loadMoreComplete();

                if(list11.size()<40) {
                    list11.addAll(list1);
                    broadcastAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getActivity(), "没有更多的数据了", Toast.LENGTH_SHORT).show();
                }

            }

        });
        broadcastAdapter.setInterfacecallback(this);




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
    public void setListResult(BroadCastListBean broadCastListBean) {
//        Log.e("TAG","++++++++++"+broadCastListBean.getList().get(0).getTitle());
        list1 = broadCastListBean.getList();
        list11.clear();
        list11.addAll(list1);

    }

    @Override
    public void setResult(BroadCastBean broadCastBean) {
//        Log.e("TAG","------>"+broadCastBean.getData().getBigImg().get(0).getTitle());
        list2 = broadCastBean.getData().getBigImg();
        list22.clear();
        list22.addAll(list2);
    }



    @Override
    public void setMessage(String msg) {

    }
    @Override
    public void setPresenter(BroadcastContarct.Presenter presenter) {
        this.presenter = presenter;

    }

    @Override
    public void Interfacecallback(int position) {
        if(position == 1) {
            Intent intent = new Intent(getActivity(), BroadcastWebActivity.class);
            String url = list2.get(0).getUrl();
            intent.putExtra("name",url);
            App.context.startActivity(intent);
        }else {
            Intent intent1 = new Intent(getActivity(), BroadcastSpActivity.class);
            BroadCastListBean.ListBean bean = list1.get(position-1);
            intent1.putExtra("name",bean);
            App.context.startActivity(intent1);
        }



    }
}
