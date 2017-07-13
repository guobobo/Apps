package com.example.iu.myapplication.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by dell on 2017/7/12.
 */
//fragment的基类
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);

        ButterKnife.bind(this,view);


        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if(hidden){
            onShow();
        }else {
            onHidden();
        }

    }

    //初始化布局
    public abstract int getLayoutId();

    //初始化数据
    public abstract void initView(View view);

    //加载数据
    public abstract void loadDate();

    //fragment显示时调用该方法
    private void onShow(){}

    //fragment隐藏时调用
    private void onHidden(){}


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
