package com.example.iu.myapplication.module.chinalive.item;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iu.myapplication.R;
import com.example.iu.myapplication.model.entity.ChinaListBean;

import java.util.ArrayList;

public class ChinaItemAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<ChinaListBean.LiveBean> list;

    public ChinaItemAdapter(Context context, ArrayList<ChinaListBean.LiveBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_china, null);
        return new MHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MHolder mHolder= (MHolder) holder;
        mHolder.item_china_title.setText(list.get(position).getTitle());
        mHolder.item_china_brief.setText(list.get(position).getBrief());
        Glide.with(context).load(list.get(position).getImage()).into(mHolder.item_china_image);
        mHolder.item_china_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = mHolder.item_china_checkbox.isChecked();
                if (checked) {
                    mHolder.item_china_checkbox.setChecked(true);
                    mHolder.item_china_linear.setVisibility(View.VISIBLE);
                } else {
                    mHolder.item_china_checkbox.setChecked(false);
                    mHolder.item_china_linear.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MHolder extends RecyclerView.ViewHolder {
        private ImageView item_china_image;
        private TextView item_china_title;
        private CheckBox item_china_checkbox;
        private TextView item_china_brief;
        private LinearLayout item_china_linear;
        public MHolder(View itemView) {
            super(itemView);
            item_china_image= (ImageView) itemView.findViewById(R.id.item_china_image);
            item_china_title= (TextView) itemView.findViewById(R.id.item_china_title);
            item_china_checkbox= (CheckBox) itemView.findViewById(R.id.item_china_checkbox);
            item_china_brief= (TextView) itemView.findViewById(R.id.item_china_brief);
            item_china_linear= (LinearLayout) itemView.findViewById(R.id.item_china_linear);
        }
    }
}
