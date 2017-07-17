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
import com.example.iu.myapplication.model.entity.HomeBean;

import java.util.List;




/**
 * Created by Administrator on 2017/7/13.
 * 精彩推荐的Adapter
 */

public class Home_Wonderful_Adapter extends RecyclerView.Adapter {

    //     精彩推荐的  监听接口
    public interface Wonder_Onclick {
        void Wonder_getOnclick(View view, int postion);
    }
    private Wonder_Onclick wonder_onclick;
    public void Wonder_setOnclick(Wonder_Onclick wonder_onclick) {
        this.wonder_onclick = wonder_onclick;
    }


    private Context activity;
    private List<HomeBean.DataBean.AreaBean.ListscrollBean> listscroll;

    public Home_Wonderful_Adapter(Context activity, List<HomeBean.DataBean.AreaBean.ListscrollBean> listscroll) {
        this.activity = activity;
        this.listscroll = listscroll;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.area_recy_item, null);


        return new My_View(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        My_View my_view = (My_View) holder;

        my_view.textView.setText(listscroll.get(position).getTitle());

        Glide.with(activity).load(listscroll.get(position).getImage()).into(my_view.imageView);


//        精彩直播的点击事件
        my_view.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                wonder_onclick.Wonder_getOnclick(v,position);

            }
        });



    }

    @Override
    public int getItemCount() {
        return 9;
    }

    class My_View extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public My_View(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.area_item_image);

            textView = (TextView) itemView.findViewById(R.id.area_item_text);

        }
    }


}
