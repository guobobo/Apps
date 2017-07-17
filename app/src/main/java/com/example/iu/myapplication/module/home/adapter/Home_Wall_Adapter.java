package com.example.iu.myapplication.module.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iu.myapplication.R;
import com.example.iu.myapplication.model.entity.HomeBean;

import java.util.ArrayList;

/**
 * Created by dell on 2017/7/17.
 */

public class Home_Wall_Adapter extends RecyclerView.Adapter {

    private ArrayList<HomeBean.DataBean.WallliveBean.ListBeanXX> listBeanXXes;
    private Context context;

    public Home_Wall_Adapter(ArrayList<HomeBean.DataBean.WallliveBean.ListBeanXX> listBeanXXes, Context context) {
        this.listBeanXXes = listBeanXXes;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(context, R.layout.live_recy_item,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        MyViewHolder myViewHolder = (MyViewHolder) holder;

        Glide.with(context).load(listBeanXXes.get(position).getImage()).into(myViewHolder.live_recy_image);

        myViewHolder.live_recy_text.setText(listBeanXXes.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return listBeanXXes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView live_recy_image;
        TextView live_recy_text;

        public MyViewHolder(View itemView) {
            super(itemView);
            live_recy_image = (ImageView) itemView.findViewById(R.id.live_recy_image);
            live_recy_text = (TextView) itemView.findViewById(R.id.live_recy_text);
        }
    }
}
