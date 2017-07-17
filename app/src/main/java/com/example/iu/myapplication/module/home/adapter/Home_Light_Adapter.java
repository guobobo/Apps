package com.example.iu.myapplication.module.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iu.myapplication.R;
import com.example.iu.myapplication.model.entity.Home_China_Movie_Text;

import java.util.ArrayList;

/**
 * Created by dell on 2017/7/17.
 */

public class Home_Light_Adapter extends BaseAdapter {

    private ArrayList<Home_China_Movie_Text.ListBean> listBeen;
    private Context context;
    private MyViewHolder myViewHolder;

    public Home_Light_Adapter(ArrayList<Home_China_Movie_Text.ListBean> listBeen, Context context) {
        this.listBeen = listBeen;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return listBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView ==null){

            myViewHolder = new MyViewHolder();

            convertView = View.inflate(context, R.layout.light_recy_item,null);

            myViewHolder.light_image = (ImageView) convertView.findViewById(R.id.light_image);
            myViewHolder.light_content = (TextView) convertView.findViewById(R.id.light_content);
            myViewHolder.light_time = (TextView) convertView.findViewById(R.id.light_time);

            convertView.setTag(myViewHolder);
        }else {

            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        Glide.with(context).load(listBeen.get(position).getImage()).into(myViewHolder.light_image);

        myViewHolder.light_content.setText(listBeen.get(position).getTitle());

        myViewHolder.light_time.setText(listBeen.get(position).getDaytime());

        return convertView;
    }

    static class MyViewHolder {
        ImageView light_image;
        TextView light_content;
        TextView light_time;
    }
}
