package com.example.iu.myapplication.module.home;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.iu.myapplication.R;
import com.example.iu.myapplication.base.BaseFragment;
import com.example.iu.myapplication.model.entity.HomeBean;
import com.example.iu.myapplication.module.home.adapter.HomeFragmentAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by dell on 2017/7/12.
 */

public class HomeFragment extends BaseFragment implements HomeContarct.View {

    @Bind(R.id.home_xrecy)
    XRecyclerView homeXrecy;

    private HomeContarct.Presenter presenter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {

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
    public void setResult(HomeBean homeBean) {
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(homeBean);
        HomeFragmentAdapter adapter = new HomeFragmentAdapter(list,getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        homeXrecy.setLayoutManager(manager);
        homeXrecy.setAdapter(adapter);
    }

    @Override
    public void setMessage(String msg) {
    }

    @Override
    public void setPresenter(HomeContarct.Presenter presenter) {
        this.presenter = presenter;
    }
}
