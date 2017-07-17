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
    private ArrayList<Object> home_data_object = new ArrayList<>();
    private ArrayList<HomeBean.DataBean> list = new ArrayList<HomeBean.DataBean>();

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

        home_data_object.clear();
        home_data_object.add(homeBean.getData().getBigImg().get(0));
        home_data_object.add(homeBean.getData().getArea());
        home_data_object.add(homeBean.getData().getPandaeye());
        home_data_object.add(homeBean.getData().getPandalive());
        home_data_object.add(homeBean.getData().getWalllive());
        home_data_object.add(homeBean.getData().getChinalive());
        home_data_object.add(homeBean.getData().getInteractive());
        home_data_object.add(homeBean.getData().getCctv());
        home_data_object.add(homeBean.getData().getList().get(0));

        list.clear();

        HomeBean.DataBean data = homeBean.getData();

        list.add(data);

        HomeFragmentAdapter adapter = new HomeFragmentAdapter(home_data_object, getActivity(), list);

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
