package com.example.iu.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iu.myapplication.model.entity.WorkBean;

import java.util.ArrayList;

/**
 * Created by iu on 2017/7/23.
 */

public class CollectionAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<WorkBean> list;
    private MyViewHolder viewHolder;

    public CollectionAdapter(Context context, ArrayList<WorkBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            viewHolder = new MyViewHolder();

            convertView = View.inflate(context,R.layout.history_item,null);

//            viewHolder.history_item_checkBox = (CheckBox) convertView.findViewById(R.id.history_item_checkBox);
            viewHolder.history_item_imge = (ImageView) convertView.findViewById(R.id.history_item_imge);
            viewHolder.history_item_durationText = (TextView) convertView.findViewById(R.id.history_item_durationText);
            viewHolder.history_item_titleText = (TextView) convertView.findViewById(R.id.history_item_titleText);
            viewHolder.history_item_tiemText = (TextView) convertView.findViewById(R.id.history_item_tiemText);

            convertView.setTag(viewHolder);

        }else {

            viewHolder = (MyViewHolder) convertView.getTag();
        }

        Glide.with(context).load(list.get(position).getImageUrl()).into(viewHolder.history_item_imge);
        viewHolder.history_item_durationText.setText(list.get(position).getVoideLength());
        viewHolder.history_item_titleText.setText(list.get(position).getTitle());
        viewHolder.history_item_tiemText.setText(list.get(position).getDayTime());

//        if(list.get(position).isVisibility()){
//            viewHolder.history_item_checkBox.setVisibility(View.VISIBLE);
//        }else {
//            viewHolder.history_item_checkBox.setVisibility(View.GONE);
//        }
//        boolean flag = list.get(position).isFlag();
//        viewHolder.history_item_checkBox.setChecked(flag);

        return convertView;
    }


    static class MyViewHolder{
        CheckBox history_item_checkBox;
        ImageView history_item_imge;
        TextView history_item_durationText;
        TextView history_item_titleText;
        TextView history_item_tiemText;

    }
}
