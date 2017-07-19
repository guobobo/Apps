package com.example.iu.myapplication.module.home;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.iu.myapplication.R;
import com.example.iu.myapplication.base.BaseFragment;
import com.example.iu.myapplication.model.entity.HomeBean;
import com.example.iu.myapplication.model.entity.Home_CCTV_Bean;
import com.example.iu.myapplication.model.entity.Home_China_Movie_Text;
import com.example.iu.myapplication.model.entity.Look_Down_Text;
import com.example.iu.myapplication.module.home.adapter.HomeFragmentAdapter;
import com.example.iu.myapplication.module.pandabroadcast.activity.BroadcastWebActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by dell on 2017/7/12.
 */

public class HomeFragment extends BaseFragment implements HomeContarct.View ,HomeFragmentAdapter.x_Recy_Onclick {

    @Bind(R.id.home_xrecy)
    XRecyclerView homeXrecy;
    private ArrayList<Object> home_data_object = new ArrayList<>();
    private ArrayList<HomeBean.DataBean> list = new ArrayList<HomeBean.DataBean>();

    private HomeContarct.Presenter presenter;
    private HomeFragmentAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {

        homeXrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                adapter.notifyDataSetChanged();
                homeXrecy.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                Toast.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                homeXrecy.refreshComplete();
            }
        });
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

        adapter = new HomeFragmentAdapter(home_data_object, getActivity(), list);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());

        homeXrecy.setLayoutManager(manager);

        homeXrecy.setAdapter(adapter);


        adapter.setx_Recy_Onclick(this);
    }

    @Override
    public void setMessage(String msg) {
    }

    @Override
    public void setPresenter(HomeContarct.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void get_Ratation_Click(View v, HomeBean.DataBean.BigImgBean bigImgBean) {

        String order = bigImgBean.getOrder();

        int i = Integer.parseInt(order);

        HomeBean.DataBean dataBean = list.get(0);

        List<HomeBean.DataBean.BigImgBean> bigImg = dataBean.getBigImg();

        String url = bigImg.get(i - 1).getUrl();

        Intent intent = new Intent(getActivity(),BroadcastWebActivity.class);

        intent.putExtra("name",url);

        getActivity().startActivity(intent);
    }

    @Override
    public void get_wonderful_Click(HomeBean.DataBean.AreaBean.ListscrollBean home_data) {

        String url = home_data.getUrl();

        Toast.makeText(getActivity(), "精彩视频的地址："+url, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void get_pandan_loog_Click(View look_view, HomeBean.DataBean.PandaeyeBean.ItemsBean itemsBean) {

        String url = itemsBean.getUrl();

        Toast.makeText(getActivity(), "熊猫观察新生的地址："+url, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void get_pandan_loog_second_Click(View look_view, HomeBean.DataBean.PandaeyeBean.ItemsBean second_itemsBean) {

        String url = second_itemsBean.getUrl();

        Toast.makeText(getActivity(), "熊猫观察趣闻的地址："+url, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void get_pandan_look_down_Click(Look_Down_Text.ListBean look_down_text) {

        String url = look_down_text.getUrl();
        Toast.makeText(getActivity(), "熊猫观察列表的地址："+url, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void get_Panda_live_Click(HomeBean.DataBean.PandaliveBean pandalivebean) {

    }

    @Override
    public void get_great_live_Click(HomeBean.DataBean.WallliveBean listBeanX) {

    }

    @Override
    public void get_china_live_Click(HomeBean.DataBean.ChinaliveBean listBeanXX) {

    }

    @Override
    public void get_special_planning_Click(View v, HomeBean.DataBean.InteractiveBean.InteractiveoneBean interactiveoneBean) {
                //get_special_planning_Click
        Intent intent = new Intent(getActivity(),BroadcastWebActivity.class);

        intent.putExtra("name",interactiveoneBean.getUrl());

        getActivity().startActivity(intent);
    }

    @Override
    public void get_cctv_live_Click(Home_CCTV_Bean.ListBean listBean) {

    }

    @Override
    public void get_movie_live_Click(Home_China_Movie_Text.ListBean listBean) {

    }
}
