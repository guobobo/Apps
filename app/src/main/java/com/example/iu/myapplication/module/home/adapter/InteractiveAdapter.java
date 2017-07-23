package com.example.iu.myapplication.module.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iu.myapplication.R;
import com.example.iu.myapplication.model.entity.InteractivesBean;

import java.util.ArrayList;

/**
 * Created by iu on 2017/7/12.
 */

public class InteractiveAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<InteractivesBean.InteractiveBean> list;
    private MyHolder myHolder;
    private MyOnClickListener listener;

    public void setListener(MyOnClickListener listener){

        this.listener = listener;
    }


    public InteractiveAdapter(Context context, ArrayList<InteractivesBean.InteractiveBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.interactive_r_item, null);

        myHolder = new MyHolder(inflate , listener);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        myHolder = (MyHolder) holder;
        Glide.with(context).load(list.get(position).getImage()).into(((MyHolder) holder).img);
        myHolder.tv1.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView tv1;
        private MyOnClickListener listener;

        public MyHolder(View itemView , final MyOnClickListener listener) {
            super(itemView);
            this.listener = listener;
            img = (ImageView) itemView.findViewById(R.id.interactive_r_item_img);
            tv1 = (TextView) itemView.findViewById(R.id.interactive_r_item_tv1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.setOnClick(getAdapterPosition());
                }
            });
        }
    }


    public interface MyOnClickListener{

        void setOnClick(int pos);
    }

}