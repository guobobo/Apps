package com.example.iu.myapplication.module.pandaculture.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iu.myapplication.App;
import com.example.iu.myapplication.R;
import com.example.iu.myapplication.config.GlideImageLoader;
import com.example.iu.myapplication.model.entity.CultureBean;
import com.example.iu.myapplication.module.pandaculture.activity.CultureWebActivity;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iu on 2017/7/12.
 */

public class CultureAdapter extends RecyclerView.Adapter {
    private List<CultureBean.BigImgBean> list1;
    private List<CultureBean.ListBean> list2;
    private Context context;
    private View inflate;


    public CultureAdapter(List<CultureBean.BigImgBean> list1, List<CultureBean.ListBean> list2, Context context) {
        this.list1 = list1;
        this.list2 = list2;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
            case 0:
                inflate = View.inflate(context, R.layout.culture_r_itema,null);
                break;
            case 1:
                inflate = View.inflate(context, R.layout.culture_r_itemb,null);
                break;
        }


        return new MyHoder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyHoder h= (MyHoder) holder;
        switch (getItemViewType(position)){
            case 0:
                ArrayList<String> list = new ArrayList<String>();

                for(int i = 0; i < list1.size(); i++) {
                    String url1 = list1.get(i).getImage();
                    list.add(url1);
                }

                //设置图片加载器
                h.banner.setImageLoader(new GlideImageLoader());
                //设置自动轮播，默认为true
                h.banner.isAutoPlay(true);
                //设置轮播时间
                h.banner.setDelayTime(2000);
                //设置图片集合
                h.banner.setImages(list);
                //banner设置方法全部调用完毕时最后调用
                h.banner.start();
                h.banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Intent intent = new Intent(context, CultureWebActivity.class);
                        CultureBean.BigImgBean bigImgBean = list1.get(position);
                        intent.putExtra("name",bigImgBean);
                        App.context.startActivity(intent);
                    }
                });
                break;
            case 1:
                Glide.with(context).load(list2.get(position).getImage()).into(h.img);
                h.tv1.setText(list2.get(position).getTitle());
                h.tv2.setText(list2.get(position).getBrief());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list2.size();
    }
    class MyHoder extends RecyclerView.ViewHolder {


        private final TextView tv1;
        private final TextView tv2;
        private final ImageView img;
        private final Banner banner;

        public MyHoder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.culture_r_itemb_tv1);
            tv2 = (TextView) itemView.findViewById(R.id.culture_r_itemb_tv2);
            img = (ImageView) itemView.findViewById(R.id.culture_r_itemb_img);
            banner = (Banner) itemView.findViewById(R.id.culture_r_itema_banner);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setListentoevents.Interfacecallback(getAdapterPosition());
                }
            });
        }
    }
    public interface  setListentoevents{
        void Interfacecallback(int position);
    }
    private setListentoevents setListentoevents;

    public void setInterfacecallback(setListentoevents setListentoevents){
        this.setListentoevents=setListentoevents;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) {
            return 0;
        }else{
            return 1;
        }
    }
}
