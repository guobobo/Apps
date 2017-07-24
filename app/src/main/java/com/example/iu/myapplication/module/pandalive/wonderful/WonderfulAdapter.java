package com.example.iu.myapplication.module.pandalive.wonderful;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iu.myapplication.R;
import com.example.iu.myapplication.model.entity.WonderfulBean;

import java.util.ArrayList;

public class WonderfulAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<WonderfulBean.VideoBean> list;

    public WonderfulAdapter(Context context, ArrayList<WonderfulBean.VideoBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wond_recy, null);
        return new MHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MHolder mHolder= (MHolder) holder;
        Glide.with(context).load(list.get(position).getImg()).asBitmap().into(mHolder.recy_wond_img);
        mHolder.recy_wond_title.setText(list.get(position).getT());
        mHolder.recy_wond_ptime.setText(list.get(position).getPtime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MHolder extends RecyclerView.ViewHolder {
        private ImageView recy_wond_img;
        private TextView recy_wond_title;
        private TextView recy_wond_ptime;
        public MHolder(View itemView) {
            super(itemView);
            recy_wond_img= (ImageView) itemView.findViewById(R.id.recy_wond_img);
            recy_wond_title= (TextView) itemView.findViewById(R.id.recy_wond_title);
            recy_wond_ptime= (TextView) itemView.findViewById(R.id.recy_wond_ptime);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onWonderful.onWonderful(getAdapterPosition());
                }
            });
        }
    }

    private OnWonderful onWonderful;

    public void setOnWonderful(OnWonderful onWonderful) {
        this.onWonderful = onWonderful;
    }

    public interface OnWonderful{
        void onWonderful(int position);
    }

}
