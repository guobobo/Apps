package com.example.iu.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.iu.myapplication.model.dao.DaoMaster;
import com.example.iu.myapplication.model.dao.DaoSession;
import com.example.iu.myapplication.model.dao.MyHistoryHelper;
import com.example.iu.myapplication.model.dao.Work;
import com.example.iu.myapplication.model.dao.WorkDao;
import com.example.iu.myapplication.model.entity.WorkBean;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dell on 2017/7/18.
 */

public class CollectionAreaFragment extends Fragment {

    @Bind(R.id.history_backImage)
    ImageView historyBackImage;
    @Bind(R.id.history_editBtn)
    Button historyEditBtn;
    @Bind(R.id.history_listview)
    ListView historyListview;
    @Bind(R.id.history_item_chooseBtn)
    Button historyItemChooseBtn;
    @Bind(R.id.history_item_deleteBtn)
    Button historyItemDeleteBtn;
    @Bind(R.id.liner_chooseAnddelete)
    LinearLayout linerChooseAnddelete;
    @Bind(R.id.activity_history)
    LinearLayout activityHistory;

    private LinkedList<Work> mList = new LinkedList<Work>();
    private ArrayList<WorkBean> hList=new ArrayList<>();
    private boolean flag = false;
    private int number = 0;
    private CollectionAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.collection_area, null);


        select();
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.history_backImage, R.id.history_editBtn, R.id.history_listview, R.id.history_item_chooseBtn, R.id.history_item_deleteBtn, R.id.liner_chooseAnddelete, R.id.activity_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.history_backImage:

                getActivity().finish();

                break;
            case R.id.history_editBtn:

                String btnText = historyEditBtn.getText().toString();
                if(btnText.equals("编辑")){
                    for(int i = 0; i < hList.size(); i++) {
                        hList.get(i).setVisibility(true);
                    }
                    linerChooseAnddelete.setVisibility(View.VISIBLE);
                    historyEditBtn.setText("取消");
                    adapter.notifyDataSetChanged();

                }else if(btnText.equals("取消")){
                    for(int i = 0; i < hList.size(); i++) {
                        hList.get(i).setVisibility(false);
                    }
                    linerChooseAnddelete.setVisibility(View.GONE);
                    historyEditBtn.setText("编辑");
                    adapter.notifyDataSetChanged();
                }


                break;
            case R.id.history_listview:


                WorkBean workBean = hList.get(0);
                if(workBean.isFlag()==false){
                    workBean.setFlag(true);
                    number++;
                    historyItemDeleteBtn.setText("删除"+"("+number+")");
                }else {
                    number--;
                    historyItemDeleteBtn.setText("删除"+"("+number+")");
                    workBean.setFlag(false);
                }
                adapter.notifyDataSetChanged();

                break;
            case R.id.history_item_chooseBtn:

                String historyBtn = historyItemChooseBtn.getText().toString();

                if(historyBtn.equals("全选")){
                    for(int i = 0; i < hList.size(); i++) {
                        hList.get(i).setFlag(true);
                    }
                    historyItemChooseBtn.setText("取消");
                    historyItemDeleteBtn.setText("删除"+"("+hList.size()+")");
                    historyItemDeleteBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            historyEditBtn.setText("编辑");
                            linerChooseAnddelete.setVisibility(View.GONE);
                            historyItemDeleteBtn.setText("删除");
                        }
                    });
                }else if(historyBtn.equals("取消")){
                    for(int i = 0; i < hList.size(); i++) {
                        hList.get(i).setFlag(false);
                    }
                    historyItemChooseBtn.setText("全选");
                    historyItemDeleteBtn.setText("删除");

                }

                break;
            case R.id.history_item_deleteBtn:
                break;
            case R.id.liner_chooseAnddelete:
                break;
            case R.id.activity_history:
                break;
        }
    }

    //查询数据库同时给listview加载数据。。
    public void select() {

        DaoMaster master = new DaoMaster(MyHistoryHelper.gethelper(getActivity()).getR());

        DaoSession session = master.newSession();

        WorkDao workDao = session.getWorkDao();



        QueryBuilder<Work> workQueryBuilder = workDao.queryBuilder();

        List<Work> list = workQueryBuilder.list();

        hList.clear();
        for(int i = 0; i <list.size(); i++) {

            hList.add(new WorkBean(list.get(i).getTitle(),list.get(i).getImage(),list.get(i).getDuration(),list.get(i).getData(),false,false));
        }

//        mList.clear();
        adapter = new CollectionAdapter(getActivity(),hList,flag);
        historyListview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void deleteAll() {


        DaoMaster master = new DaoMaster(MyHistoryHelper.gethelper(getActivity()).getW());

        DaoSession session = master.newSession();

        WorkDao workDao = session.getWorkDao();

        workDao.deleteAll();
    }


    public void updata(Work work){

        DaoMaster master = new DaoMaster(MyHistoryHelper.gethelper(getActivity()).getW());

        DaoSession session = master.newSession();

        WorkDao workDao = session.getWorkDao();

        workDao.update(work);
    }


}
