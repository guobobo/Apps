package com.example.iu.myapplication.module.pandabroadcast.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iu.myapplication.R;
import com.example.iu.myapplication.model.entity.BroadCastBean;
import com.example.iu.myapplication.model.entity.BroadCastListBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by iu on 2017/7/17.
 */

public class BroadcastAdapter extends RecyclerView.Adapter {
    private List<BroadCastBean.DataBean.BigImgBean> list;
    private List<BroadCastListBean.ListBean> list1;
    private Context context;
    private View inflate;

    public BroadcastAdapter(List<BroadCastBean.DataBean.BigImgBean> list, List<BroadCastListBean.ListBean> list1, Context context) {
        this.list = list;
        this.list1 = list1;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
            case 0:
                inflate = View.inflate(context, R.layout.broadcast_r_itema,null);
                break;
            case 1:
                inflate = View.inflate(context, R.layout.broadcast_r_itemb,null);
                break;
        }


        return new MyHoder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyHoder h= (MyHoder) holder;
        switch (getItemViewType(position)){
            case 0:
                Glide.with(context).load(list.get(position).getImage()).into(h.aimg);
                h.atv.setText(list.get(position).getTitle());

                break;
            case 1:
                Glide.with(context).load(list1.get(position).getPicurl2()).into(h.bimg1);
                h.btv1.setText(list1.get(position).getTitle());

                long focus_date = list1.get(position).getFocus_date();
                Date d = new Date(focus_date);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String format = sdf.format(d);
                h.btv2.setText(format);
                h.btv3.setText(list1.get(position).getVideolength());

                break;

        }


    }

    @Override
    public int getItemCount() {
        return list1.size();
    }
    class MyHoder extends RecyclerView.ViewHolder {


        private final ImageView aimg;
        private final ImageView bimg1;
        private final TextView btv1;
        private final TextView btv2;
        private final TextView btv3;
        private final TextView atv;

        public MyHoder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setListentoevents.Interfacecallback(getAdapterPosition());
                }
            });
            aimg = (ImageView) itemView.findViewById(R.id.broadcast_r_itema_img);
            atv = (TextView) itemView.findViewById(R.id.broadcast_r_itema_tv);
            bimg1 = (ImageView) itemView.findViewById(R.id.broadcast_r_itemb_img1);
            btv1 = (TextView) itemView.findViewById(R.id.broadcast_r_itemb_tv1);
            btv2 = (TextView) itemView.findViewById(R.id.broadcast_r_itemb_tv2);
            btv3 = (TextView) itemView.findViewById(R.id.broadcast_r_itemb_tv3);
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
            return  0;
        }else{
            return 1;
        }
    }

}

