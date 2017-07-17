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

public class Home_Live_Adapter extends RecyclerView.Adapter {

    private ArrayList<HomeBean.DataBean.PandaliveBean.ListBeanX> listBeanXes;
    private Context context;

    public Home_Live_Adapter(ArrayList<HomeBean.DataBean.PandaliveBean.ListBeanX> listBeanXes, Context context) {
        this.listBeanXes = listBeanXes;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(context, R.layout.live_recy_item,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHolder myViewHolder = (MyViewHolder) holder;

        Glide.with(context).load(listBeanXes.get(position).getImage()).into(myViewHolder.live_recy_image);

        myViewHolder.live_recy_text.setText(listBeanXes.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return listBeanXes.size();
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
