package com.example.iu.myapplication.module.chinalive;

import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iu.myapplication.R;
import com.example.iu.myapplication.base.BaseFragment;
import com.example.iu.myapplication.customize.CustomViewPager;
import com.example.iu.myapplication.model.entity.ChinaBean;
import com.example.iu.myapplication.module.chinalive.adapter.LiveChinaTabAdapter;
import com.example.iu.myapplication.module.chinalive.item.ChinaItemFragment;
import com.example.iu.myapplication.module.chinalive.item.ChinaItemPresenter;
import com.example.iu.myapplication.module.chinalive.item.GridAllAdapter;
import com.example.iu.myapplication.module.chinalive.item.GridTabAdapter;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by dell on 2017/7/12.
 */

public class ChinaFragment extends BaseFragment implements ChinaContarct.View {

    @Bind(R.id.china_layout)
    CustomViewPager chinaLayout;
    @Bind(R.id.live_china_tab)
    TabLayout liveChinaTab;
    @Bind(R.id.live_china_add_channel)
    ImageView liveChinaAddChannel;
    private ChinaContarct.Presenter presenter;

    private List<ChinaBean.TablistBean> tablistBeen = new ArrayList<>();
    private List<ChinaBean.AlllistBean> alllistBeen = new ArrayList<>();
    private GridView china_all_grid;
    private GridView china_tab_grid;
    private GridTabAdapter tabAdapter;
    private GridAllAdapter allAdapter;
    private ArrayList<Fragment> list = new ArrayList<>();
    private LiveChinaTabAdapter liveChinaTabAdapter;
    private ImageView delect_channel;
    private PopupWindow popupWindow;
    private Button live_china_edit;
    private ProgressBar progressBar;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_livecn;
    }

    @Override
    public void initView(View view) {
        progressBar = (ProgressBar) view.findViewById(R.id.china_progress);
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


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.live_china_add_channel)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.live_china_add_channel:
                initpopupwindow();
                popupWindow.setTouchable(true);
                popupWindow.setFocusable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                popupWindow.update();
                delect_channel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Window window = getActivity().getWindow();
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        popupWindow.dismiss();
                    }
                });
            break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initpopupwindow() {
        Window window = getActivity().getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.china_popupwindow, null);
        final TextView china_text = (TextView) view1.findViewById(R.id.china_text);
        china_tab_grid = (GridView) view1.findViewById(R.id.china_tab_grid);
        china_all_grid = (GridView) view1.findViewById(R.id.china_all_grid);
        delect_channel = (ImageView) view1.findViewById(R.id.live_china_delect_channel);
        popupWindow = new PopupWindow(view1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        live_china_edit = (Button) view1.findViewById(R.id.live_china_edit);
        inittabGridView();
        initallGridView();

        live_china_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (live_china_edit.getText().equals("编辑")) {
                    tabAdapter.EDIT_TRUE=true;
                    live_china_edit.setText("完成");
                    china_text.setVisibility(View.VISIBLE);



                    tabAdapter.notifyDataSetChanged();
                } else {
                    tabAdapter.notifyDataSetChanged();
                    tabAdapter.EDIT_TRUE=false;
                    live_china_edit.setText("编辑");
                    china_text.setVisibility(View.GONE);
                    list.clear();
                    for (int i = 0; i < tablistBeen.size(); i++) {
                        ChinaItemFragment childfragment = new ChinaItemFragment(tablistBeen.get(i).getUrl());
                        new ChinaItemPresenter(childfragment);
                        list.add(childfragment);
                    }
                    tabAdapter.notifyDataSetChanged();
                    liveChinaTabAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void inittabGridView() {

        china_tab_grid.setAdapter(tabAdapter);
        china_tab_grid.setNumColumns(3);
        china_tab_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(tablistBeen.size()<5) {
                    Toast.makeText(getContext(),"栏目区，不能少于四个频道",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(live_china_edit.getText().equals("完成")) {
                        ChinaBean.AlllistBean all =new ChinaBean.AlllistBean();
                        all.setTitle(tablistBeen.get(position).getTitle());
                        all.setOrder(tablistBeen.get(position).getOrder());
                        all.setType(tablistBeen.get(position).getType());
                        all.setUrl(tablistBeen.get(position).getUrl());
                        alllistBeen.add(all);
                        allAdapter.notifyDataSetChanged();
                        tablistBeen.remove(tablistBeen.get(position));
                        tabAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    public void initallGridView() {

        china_all_grid.setAdapter(allAdapter);
        china_all_grid.setNumColumns(3);
        china_all_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(live_china_edit.getText().equals("完成")) {

                    ChinaBean.TablistBean tab =new ChinaBean.TablistBean();
                    tab.setTitle(alllistBeen.get(position).getTitle());
                    tab.setOrder(alllistBeen.get(position).getOrder());
                    tab.setType(alllistBeen.get(position).getType());
                    tab.setUrl(alllistBeen.get(position).getUrl());

                    tablistBeen.add(tab);
                    alllistBeen.remove(alllistBeen.get(position));
                    allAdapter.notifyDataSetChanged();
                    tabAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void setResult(ChinaBean chinaBean) {

        if(chinaBean!=null) {
            progressBar.setVisibility(View.GONE);
        }

        list.clear();
        alllistBeen.clear();
        tablistBeen.clear();
        alllistBeen.addAll(chinaBean.getAlllist());
        tablistBeen.addAll(chinaBean.getTablist());
        tabAdapter = new GridTabAdapter(getContext(), (ArrayList<ChinaBean.TablistBean>) tablistBeen);

        for (int i = 0; i < tablistBeen.size(); i++) {
            ChinaBean.TablistBean tablistBean = tablistBeen.get(i);
            for (int j = 0; j < alllistBeen.size(); j++) {
                if(tablistBean.getTitle().equals(alllistBeen.get(j).getTitle())){
                    alllistBeen.remove(j);
                }
            }
        }

        allAdapter = new GridAllAdapter(getContext(), (ArrayList<ChinaBean.AlllistBean>) alllistBeen);
        for (int i = 0; i < chinaBean.getTablist().size(); i++) {
            ChinaItemFragment childfragment = new ChinaItemFragment(tablistBeen.get(i).getUrl());
            new ChinaItemPresenter(childfragment);
            list.add(childfragment);
        }

        liveChinaTabAdapter = new LiveChinaTabAdapter(getActivity().getSupportFragmentManager(), list, (ArrayList<ChinaBean.TablistBean>) tablistBeen);
        chinaLayout.setAdapter(liveChinaTabAdapter);
        chinaLayout.setScanScroll(false);
        liveChinaTab.setupWithViewPager(chinaLayout);
        liveChinaTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        liveChinaTab.setSelectedTabIndicatorColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        liveChinaTab.setTabTextColors(ContextCompat.getColor(getActivity(), R.color.colorBlank), ContextCompat.getColor(getActivity(), R.color.colorPrimary));

        LinearLayout linearLayout = (LinearLayout) liveChinaTab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getContext(),
                R.drawable.radio_bg));

    }

    @Override
    public void setMessage(String msg) {

    }

    @Override
    public void setPresenter(ChinaContarct.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(getActivity());
        MobclickAgent.onPageStart("ChinaFragment");//统计时长
    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(getActivity());
        MobclickAgent.onPageStart("ChinaFragment");
    }
}
