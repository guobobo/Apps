package com.example.iu.myapplication.module.chinalive.item;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iu.myapplication.R;
import com.example.iu.myapplication.model.entity.ChinaBean;

import java.util.ArrayList;

public class GridTabAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ChinaBean.TablistBean> list;

    //是否处于编辑状态
    public boolean EDIT_TRUE;

    public GridTabAdapter(Context context, ArrayList<ChinaBean.TablistBean> list) {
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
        MHolder holder;
        if (convertView == null) {
            holder = new MHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_grid, null);
            holder.item_tab_title = (TextView) convertView.findViewById(R.id.item_tab_title);
            holder.edig_edit = (ImageView) convertView.findViewById(R.id.live_china_txt_edig_edit);
            convertView.setTag(holder);
        } else {
            holder = (MHolder) convertView.getTag();
        }

        holder.item_tab_title.setText(list.get(position).getTitle());

        if(EDIT_TRUE){
            holder.edig_edit.setVisibility(View.VISIBLE);
        }else {
            holder.edig_edit.setVisibility(View.GONE);
        }

        return convertView;
    }

    class MHolder {
        TextView item_tab_title;
        ImageView edig_edit;
    }

}
