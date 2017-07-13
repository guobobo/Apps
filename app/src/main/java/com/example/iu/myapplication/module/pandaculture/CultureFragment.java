package com.example.iu.myapplication.module.pandaculture;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.PullToRefreshRecyclerView;
import com.example.iu.myapplication.R;
import com.example.iu.myapplication.base.BaseFragment;
import com.example.iu.myapplication.model.entity.CultureBean;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dell on 2017/7/12.
 */

public class CultureFragment extends BaseFragment implements CultureContarct.View {
    @Bind(R.id.culturefragemnt_precy)
    PullToRefreshRecyclerView culturefragemntPrecy;

    @Override
    public int getLayoutId() {
        return R.layout.culturefragment;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void loadDate() {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void setResult(CultureBean cultureBean) {

    }


    @Override
    public void setMessage(String msg) {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.culturefragemnt_precy)
    public void onViewClicked() {
    }

    @Override
    public void setPresenter(CultureContarct.Presenter presenter) {

    }
}
