package com.example.iu.myapplication.module.pandalive.live;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iu.myapplication.R;
import com.example.iu.myapplication.base.BaseFragment;
import com.example.iu.myapplication.model.entity.PandaLiveBean;
import com.example.iu.myapplication.model.entity.PandaMultipleBean;
import com.example.iu.myapplication.module.pandalive.live.adapter.LiveAdapterRecy;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class LiveOnFragment extends BaseFragment implements LiveOnContarct.View {

    @Bind(R.id.duo)
    RadioButton duo;
    @Bind(R.id.bian)
    RadioButton bian;
    @Bind(R.id.live_recy)
    RecyclerView liveRecy;

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

    @Override
    public int getLayoutId() {
        return R.layout.fragment_liveon;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void loadDate() {
        presenter.start();
        presenter.multiple();
    }

    @Override
    public void setPresenter(LiveOnContarct.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setMultiple(final PandaMultipleBean pandaMultipleBean) {
        Glide.with(getContext()).load(pandaMultipleBean.getList().get(0).getImage()).asBitmap().into(liveImage);
        multipleBeen=new ArrayList<>();
        for (int i=0;i<pandaMultipleBean.getList().size();i++){
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
        liveTitle.setText(pandaLiveBean.getLive().get(0).getTitle());
        liveBrief.setText(pandaLiveBean.getLive().get(0).getBrief());
    }

    @Override
    public void setMessage(String msg) {

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

                break;
            case R.id.bian:

                break;
        }
    }

}
