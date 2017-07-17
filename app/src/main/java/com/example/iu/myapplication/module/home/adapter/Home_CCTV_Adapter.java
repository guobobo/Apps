package com.example.iu.myapplication.module.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iu.myapplication.R;
import com.example.iu.myapplication.model.entity.Home_CCTV_Bean;

import java.util.ArrayList;

/**
 * Created by dell on 2017/7/17.
 */

public class Home_CCTV_Adapter extends RecyclerView.Adapter {

    private ArrayList<Home_CCTV_Bean.ListBean> listBeen;
    private Context context;

    public Home_CCTV_Adapter(ArrayList<Home_CCTV_Bean.ListBean> listBeen, Context context) {
        this.listBeen = listBeen;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(context, R.layout.cctv_recy_item,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHolder myViewHolder = (MyViewHolder) holder;

        Glide.with(context).load(listBeen.get(position).getImage()).into(myViewHolder.cctv_image);

        myViewHolder.cctv_text.setText(listBeen.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return listBeen.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView cctv_image;
        TextView cctv_text;

        public MyViewHolder(View itemView) {
            super(itemView);

            cctv_image = (ImageView) itemView.findViewById(R.id.cctv_image);
            cctv_text = (TextView) itemView.findViewById(R.id.cctv_text);

        }
    }
}
