package com.example.iu.myapplication.module.pandalive.live.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iu.myapplication.R;
import com.example.iu.myapplication.model.entity.LookchatBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LookchatAdapterRecy extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<LookchatBean.DataBean.ContentBean> list;

    public LookchatAdapterRecy(Context context, ArrayList<LookchatBean.DataBean.ContentBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_look_recy, null);
        return new MHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MHolder mHolder= (MHolder) holder;

        mHolder.look_author.setText(list.get(position).getAuthor());
        mHolder.look_total.setText("17701楼");
        mHolder.look_message.setText(list.get(position).getMessage());
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yyyy");
        String format = sdf.format(date);
        mHolder.look_date.setText(format);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MHolder extends RecyclerView.ViewHolder {
        private TextView look_author;
        private TextView look_total;
        private TextView look_message;
        private TextView look_date;
        public MHolder(View itemView) {
            super(itemView);
            look_author = (TextView) itemView.findViewById(R.id.look_author);
            look_total = (TextView) itemView.findViewById(R.id.look_total);
            look_message = (TextView) itemView.findViewById(R.id.look_message);
            look_date = (TextView) itemView.findViewById(R.id.look_date);

        }
    }
}
