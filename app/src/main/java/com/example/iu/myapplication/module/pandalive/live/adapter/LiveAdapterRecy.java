package com.example.iu.myapplication.module.pandalive.live.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iu.myapplication.R;
import com.example.iu.myapplication.model.entity.PandaMultipleBean;

import java.util.List;


public class LiveAdapterRecy extends RecyclerView.Adapter {

    private Context context;
    private List<PandaMultipleBean.ListBean> beanList;

    public LiveAdapterRecy(Context context, List<PandaMultipleBean.ListBean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_live_recy, null);
        return new MHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MHolder mHolder = (MHolder) holder;
        mHolder.recyTitle.setText(beanList.get(position).getTitle());
        Glide.with(context).load(beanList.get(position).getImage()).asBitmap().into(mHolder.recyImage);

    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    class MHolder extends RecyclerView.ViewHolder {

        ImageView recyImage;

        TextView recyTitle;
        public MHolder(View itemView) {
            super(itemView);
            recyImage= (ImageView) itemView.findViewById(R.id.recy_image);
            recyTitle= (TextView) itemView.findViewById(R.id.recy_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myClickListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    public interface MyClickListener {
        void onItemClick(int postion);
    }
    MyClickListener myClickListener;

    public void setMyClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }
}
