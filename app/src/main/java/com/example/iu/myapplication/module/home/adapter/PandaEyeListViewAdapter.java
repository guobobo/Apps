package com.example.iu.myapplication.module.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iu.myapplication.R;
import com.example.iu.myapplication.model.entity.Look_Down_Text;

import java.util.ArrayList;

/**
 * Created by dell on 2017/7/17.
 */

public class PandaEyeListViewAdapter extends BaseAdapter {

    private ArrayList<Look_Down_Text.ListBean> list;
    private Context context;
    private MyViewHolder holder;

    public PandaEyeListViewAdapter(ArrayList<Look_Down_Text.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
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

            holder = new MyViewHolder();

            convertView = View.inflate(context, R.layout.pandaeye_listview_item,null);

            holder.eye_list_image1 = (ImageView) convertView.findViewById(R.id.eye_list_image1);
            holder.list_text1 = (TextView) convertView.findViewById(R.id.list_text1);
            holder.list_text2 = (TextView) convertView.findViewById(R.id.list_text2);

            convertView.setTag(holder);

        }else {

            holder = (MyViewHolder) convertView.getTag();
        }

        Glide.with(context).load(list.get(position).getImage()).into(holder.eye_list_image1);

        holder.list_text1.setText(list.get(position).getTitle());

        holder.list_text2.setText(list.get(position).getDaytime());

        return convertView;
    }

    static  class MyViewHolder{

        TextView list_text1;
        TextView list_text2;
        ImageView eye_list_image1;
    }
}
