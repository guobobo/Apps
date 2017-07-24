package com.example.iu.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iu.myapplication.R;
import com.example.iu.myapplication.model.entity.WorkBean;

import java.util.ArrayList;

/**
 * Created by iu on 2017/7/24.
 */

public class CollectionAdapterRecy extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<WorkBean> list;


    public CollectionAdapterRecy(Context context, ArrayList<WorkBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_item, null);
        return new MHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MHolder mHolder = (MHolder) holder;
        Glide.with(context).load(list.get(position).getImageUrl()).into(mHolder.history_item_imge);
        mHolder.history_item_durationText.setText(list.get(position).getVoideLength());
        mHolder.history_item_titleText.setText(list.get(position).getTitle());
        mHolder.history_item_tiemText.setText(list.get(position).getDayTime());

        if (list.get(position).isVisibility()) {
            mHolder.history_item_checkBox.setVisibility(View.VISIBLE);
        } else {
            mHolder.history_item_checkBox.setVisibility(View.GONE);
        }

        mHolder.history_item_checkBox.setChecked(list.get(position).isFlag());

        mHolder.history_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHistory.onHistoryListener(position);


            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MHolder extends RecyclerView.ViewHolder {
        RadioButton history_item_checkBox;
        ImageView history_item_imge;
        TextView history_item_durationText;
        TextView history_item_titleText;
        TextView history_item_tiemText;
        LinearLayout history_linear;
        public MHolder(View itemView) {
            super(itemView);
            history_item_checkBox = (RadioButton) itemView.findViewById(R.id.history_item_checkBox);
            history_item_imge = (ImageView) itemView.findViewById(R.id.history_item_imge);
            history_item_durationText = (TextView) itemView.findViewById(R.id.history_item_durationText);
            history_item_titleText = (TextView) itemView.findViewById(R.id.history_item_titleText);
            history_item_tiemText = (TextView) itemView.findViewById(R.id.history_item_tiemText);
            history_linear= (LinearLayout) itemView.findViewById(R.id.history_linear);
        }
    }

    private HistoryAdapterRecy.OnHistory onHistory;

    public void setOnHistory(HistoryAdapterRecy.OnHistory onHistory) {
        this.onHistory = onHistory;
    }

    public interface OnHistory {
        void onHistoryListener(int position);
    }
}

