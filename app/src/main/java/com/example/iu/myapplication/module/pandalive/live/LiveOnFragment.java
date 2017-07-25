package com.example.iu.myapplication.module.pandalive.live;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iu.myapplication.R;
import com.example.iu.myapplication.base.BaseFragment;
import com.example.iu.myapplication.model.entity.LookchatBean;
import com.example.iu.myapplication.model.entity.PandaLiveBean;
import com.example.iu.myapplication.model.entity.PandaMultipleBean;
import com.example.iu.myapplication.module.pandalive.live.adapter.LiveAdapterRecy;
import com.example.iu.myapplication.module.pandalive.live.adapter.LookchatAdapterRecy;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LiveOnFragment extends BaseFragment implements LiveOnContarct.View {

    @Bind(R.id.duo)
    RadioButton duo;
    @Bind(R.id.bian)
    RadioButton bian;
    @Bind(R.id.live_recy)
    RecyclerView liveRecy;
    @Bind(R.id.linear_duo)
    LinearLayout linearDuo;
    @Bind(R.id.bian_edit)
    EditText bianEdit;
    @Bind(R.id.live_recy1)
    RecyclerView liveRecy1;
    @Bind(R.id.linear_bian)
    LinearLayout linearBian;

    private LiveOnContarct.Presenter presenter;

    @Bind(R.id.live_brief)
    TextView liveBrief;
    @Bind(R.id.live_checkbox)
    CheckBox liveCheckbox;
    @Bind(R.id.live_linear)
    LinearLayout liveLinear;
    @Bind(R.id.live_image)
    ImageView liveImage;
    @Bind(R.id.live_title)
    TextView liveTitle;

    List<PandaMultipleBean.ListBean> multipleBeen;
    LiveAdapterRecy adapterRecy;
    private ProgressBar liveon;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_liveon;
    }

    @Override
    public void initView(View view) {
        liveon = (ProgressBar) view.findViewById(R.id.liveon_progress);
    }

    @Override
    public void loadDate() {
        presenter.start();
        presenter.multiple();
        presenter.lookchat();
    }

    @Override
    public void setPresenter(LiveOnContarct.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setMultiple(final PandaMultipleBean pandaMultipleBean) {

        if(pandaMultipleBean!=null){
            liveon.setVisibility(View.GONE);
        }

        Glide.with(getContext()).load(pandaMultipleBean.getList().get(0).getImage()).asBitmap().into(liveImage);
        multipleBeen = new ArrayList<>();
        for (int i = 0; i < pandaMultipleBean.getList().size(); i++) {
            PandaMultipleBean.ListBean listBean = pandaMultipleBean.getList().get(i);
            multipleBeen.add(listBean);
        }
        adapterRecy = new LiveAdapterRecy(getContext(), multipleBeen);
        liveRecy.setLayoutManager(new GridLayoutManager(getContext(), 3));
        liveRecy.setAdapter(adapterRecy);

        adapterRecy.setMyClickListener(new LiveAdapterRecy.MyClickListener() {
            @Override
            public void onItemClick(int postion) {
                String title = pandaMultipleBean.getList().get(postion).getTitle();
                String image = pandaMultipleBean.getList().get(postion).getImage();
                liveTitle.setText(title);
                Glide.with(getContext()).load(image).asBitmap().into(liveImage);
            }
        });

    }

    @Override
    public void setResult(PandaLiveBean pandaLiveBean) {
        if(pandaLiveBean!=null){
            liveon.setVisibility(View.GONE);
        }
        liveTitle.setText(pandaLiveBean.getLive().get(0).getTitle());
        liveBrief.setText(pandaLiveBean.getLive().get(0).getBrief());
    }

    @Override
    public void setMessage(String msg) {

    }

    @Override
    public void setLookchat(LookchatBean lookchatBean) {
        if(lookchatBean!=null){
            liveon.setVisibility(View.GONE);
        }
        ArrayList<LookchatBean.DataBean.ContentBean> contentBeen=new ArrayList<>();
        contentBeen.addAll(lookchatBean.getData().getContent());
        LookchatAdapterRecy lookchatAdapter=new LookchatAdapterRecy(getContext(),contentBeen);
        liveRecy1.setLayoutManager(new LinearLayoutManager(getContext()));
        liveRecy1.setAdapter(lookchatAdapter);
    }

    @OnClick({R.id.live_checkbox, R.id.duo, R.id.bian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.live_checkbox:
                boolean checked = liveCheckbox.isChecked();
                if (checked) {
                    liveCheckbox.setChecked(true);
                    liveLinear.setVisibility(View.VISIBLE);
                } else {
                    liveCheckbox.setChecked(false);
                    liveLinear.setVisibility(View.GONE);
                }
                break;
            case R.id.duo:
                linearDuo.setVisibility(View.VISIBLE);
                linearBian.setVisibility(View.GONE);
                bian.setTextColor(Color.BLACK);
                duo.setTextColor(Color.BLUE);
                break;
            case R.id.bian:
                linearBian.setVisibility(View.VISIBLE);
                linearDuo.setVisibility(View.GONE);
                duo.setTextColor(Color.BLACK);
                bian.setTextColor(Color.BLUE);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(getActivity());
        MobclickAgent.onPageStart("LiveOnFragment");//统计时长
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(getActivity());
        MobclickAgent.onPageStart("LiveOnFragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
