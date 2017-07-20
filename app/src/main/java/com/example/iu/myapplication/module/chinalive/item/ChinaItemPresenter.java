package com.example.iu.myapplication.module.chinalive.item;


import com.example.iu.myapplication.model.biz.china.ChinaModel;
import com.example.iu.myapplication.model.biz.china.ChinaModelImpl;

public class ChinaItemPresenter implements ChinaItemContarct.Presenter {
    private ChinaModel chinaModel;
    private ChinaItemContarct.View itemView;

    public ChinaItemPresenter(ChinaItemContarct.View itemView) {
        this.itemView = itemView;
        this.itemView.setPresenter(this);
        chinaModel=new ChinaModelImpl();
    }

    @Override
    public void start() {

    }

}
