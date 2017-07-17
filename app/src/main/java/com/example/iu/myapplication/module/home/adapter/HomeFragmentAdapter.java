package com.example.iu.myapplication.module.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.iu.myapplication.R;
import com.example.iu.myapplication.config.GlideImageLoader;
import com.example.iu.myapplication.model.entity.HomeBean;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/7/15.
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter{

    private ArrayList<Object> list;
    private Context context;


    public HomeFragmentAdapter(ArrayList<Object> list, Context context) {
        this.list = list;
        this.context = context;
    }

    private final int BANNER_TYPE = 1;
    private final int PANDAEYE_TYPE = 2;
    private final int LIVE_TYPE = 3;
    private final int CHINA_TYPE = 4;
    private final int CCTV_TYPE = 5;
    private final int LINGHT_TYPE = 6;


    @Override
    public int getItemViewType(int position) {

        Object o = list.get(position);

        if(o instanceof HomeBean.DataBean.BigImgBean){

            return BANNER_TYPE;
        }


        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){

            case BANNER_TYPE:
                    return new BannerHolder(View.inflate(context,R.layout.homefragment_recy_banner,null));

        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)){

            case BANNER_TYPE:

                    BannerHolder mHolder = (BannerHolder) holder;
                    HomeBean homebean = (HomeBean) list.get(position);

                    ArrayList<String> images = new ArrayList<String>();

                List<HomeBean.DataBean.BigImgBean> bigImg = homebean.getData().getBigImg();

                for(int i = 0; i < bigImg.size(); i++) {

                    HomeBean.DataBean.BigImgBean bigImgBean = bigImg.get(i);

                    String image = bigImgBean.getImage();
                    images.add(image);
                }

                    mHolder.banner.setImageLoader(new GlideImageLoader());
                    //设置自动轮播，默认为true
                    mHolder.banner.isAutoPlay(true);
                    //设置轮播时间
                    mHolder.banner.setDelayTime(2000);
                    //设置图片集合
                    mHolder.banner.setImages(list);

                    mHolder.banner.setBannerTitles(images);
                    //banner设置方法全部调用完毕时最后调用
                    mHolder.banner.start();

                break;

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class BannerHolder extends RecyclerView.ViewHolder{

        Banner banner;

        public BannerHolder(View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.home_recy_banner);
        }
    }
}
