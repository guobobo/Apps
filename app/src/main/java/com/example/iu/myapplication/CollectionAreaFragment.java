package com.example.iu.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.iu.myapplication.model.dao.DaoMaster;
import com.example.iu.myapplication.model.dao.DaoSession;
import com.example.iu.myapplication.model.dao.MyHelper;
import com.example.iu.myapplication.model.dao.MyHistoryHelper;
import com.example.iu.myapplication.model.dao.Work;
import com.example.iu.myapplication.model.dao.WorkDao;
import com.example.iu.myapplication.model.entity.WorkBean;
import com.example.iu.myapplication.module.pandabroadcast.activity.BroadcastSpActivity;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by dell on 2017/7/18.
 */

public class CollectionAreaFragment extends Fragment implements View.OnClickListener {


    private LinkedList<Work> mList = new LinkedList<Work>();
    private ArrayList<WorkBean> hList = new ArrayList<>();
    private boolean flag = false;
    private int number = 0;
    private int list;
    private ListView listView;
    private RecyclerView historyListview;
    private TextView collection_edit;
    private ListView history_listview;
    private Button history_item_chooseBtn;
    private Button history_item_deleteBtn;
    private LinearLayout liner_chooseAnddelete;
    private LinearLayout activity_history;
    private View view;
    private CheckBox history_item_checkBox;
    private Button historyItemDeleteBtn;
    private Button historyItemChooseBtn;
    private CollectionAdapterRecy collectionAdapterRecy;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.collection_area, null);

        historyListview = (RecyclerView) view.findViewById(R.id.history_listview);
        liner_chooseAnddelete = (LinearLayout) view.findViewById(R.id.liner_chooseAnddelete);
        historyItemDeleteBtn = (Button) view.findViewById(R.id.history_item_deleteBtn);
        historyItemChooseBtn = (Button) view.findViewById(R.id.history_item_chooseBtn);

        edit();
        initView(view);
        select();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    //查询数据库同时给listview加载数据。。
    public void select() {


        DaoMaster master = new DaoMaster(MyHelper.gethelper(getActivity()).getr());
        DaoSession daoSession = master.newSession();
        WorkDao workDao = daoSession.getWorkDao();

        QueryBuilder<Work> workQueryBuilder = workDao.queryBuilder();
        List<Work> list = workQueryBuilder.list();


        Log.e("TAG", "---------------------------" + list.toString());

        hList.clear();
        for (int i = 0; i < list.size(); i++) {

            hList.add(new WorkBean(list.get(i).getTitle(), list.get(i).getImage(), list.get(i).getDuration(), list.get(i).getData(), list.get(i).getUrl(), false, false));

            String title = hList.get(i).getMid();
            Log.e("TAG", "++++++++++++++++++++++" + title);
        }

        mList.clear();
        collectionAdapterRecy = new CollectionAdapterRecy(getActivity(), hList);

        historyListview.setLayoutManager(new LinearLayoutManager(getActivity()));
        historyListview.setAdapter(collectionAdapterRecy);
        collectionAdapterRecy.notifyDataSetChanged();
        collectionAdapterRecy.setOnHistory(new HistoryAdapterRecy.OnHistory() {
            @Override
            public void onHistoryListener(int position) {

                collection_edit = (TextView) getActivity().findViewById(R.id.collection_edit);


                if (collection_edit.getText().toString().equals("取消")) {
                    if (hList.get(position).isFlag() == false) {
                        hList.get(position).setFlag(true);
                        number++;
                        historyItemDeleteBtn.setText("删除" + number);
                    } else {
                        hList.get(position).setFlag(false);
                        number--;
                        historyItemDeleteBtn.setText("删除" + number);
                    }
                    if (number == 0) {
                        historyItemDeleteBtn.setText("删除");
                    }
                    if (number == hList.size()) {
                        historyItemChooseBtn.setText("取消全选");
                    } else {
                        historyItemChooseBtn.setText("全选");
                    }

                }else{

                    String title = hList.get(position).getTitle();
                    String image = hList.get(position).getImageUrl();
                    String voideLength = hList.get(position).getVoideLength();
                    String mid = hList.get(position).getMid();

                    Intent intent = new Intent(getActivity(), BroadcastSpActivity.class);
                    intent.putExtra("title", title);
                    intent.putExtra("image", image);
                    intent.putExtra("duration", voideLength);
                    intent.putExtra("id", mid);
                    startActivity(intent);


                }
                collectionAdapterRecy.notifyDataSetChanged();

            }
        });

    }

    public void deleteAll() {


        DaoMaster master = new DaoMaster(MyHistoryHelper.gethelper(getActivity()).getW());

        DaoSession session = master.newSession();

        WorkDao workDao = session.getWorkDao();

        workDao.deleteAll();
    }


    public void delete(Work work) {

        DaoMaster master = new DaoMaster(MyHistoryHelper.gethelper(getActivity()).getW());

        DaoSession session = master.newSession();

        WorkDao workDao = session.getWorkDao();

        workDao.delete(work);
    }

    private void initView(View view) {
        history_item_chooseBtn = (Button) view.findViewById(R.id.history_item_chooseBtn);
        history_item_deleteBtn = (Button) view.findViewById(R.id.history_item_deleteBtn);
        liner_chooseAnddelete = (LinearLayout) view.findViewById(R.id.liner_chooseAnddelete);
        activity_history = (LinearLayout) view.findViewById(R.id.activity_history);

        history_item_chooseBtn.setOnClickListener(this);
        history_item_deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.history_item_chooseBtn:

                //全选

                if (historyItemChooseBtn.getText().equals("全选")) {
                    historyItemChooseBtn.setText("取消全选");
                    if (collection_edit.getText().equals("取消")) {
                        for (int i = 0; i < hList.size(); i++) {
                            hList.get(i).setFlag(true);
                        }
                        number = hList.size();
                        historyItemDeleteBtn.setText("删除" + number);
                        collectionAdapterRecy.notifyDataSetChanged();
                    }
                } else {
                    for (int i = 0; i < hList.size(); i++) {
                        hList.get(i).setFlag(false);
                    }
                    number = 0;
                    historyItemDeleteBtn.setText("删除");
                    historyItemChooseBtn.setText("全选");
                    collectionAdapterRecy.notifyDataSetChanged();
                }

                break;
            case R.id.history_item_deleteBtn:

                //删除

                if (collection_edit.getText().equals("取消")) {
                    for (int i = hList.size() - 1; i >= 0; i--) {
                        if (hList.get(i).isFlag()) {
                            deleteAll();
                            hList.remove(hList.get(i));
                        }
                    }
                    number = 0;
                    collectionAdapterRecy.notifyDataSetChanged();
                    historyItemDeleteBtn.setText("删除");
                    if (hList.size() == 0) {
                        collection_edit.setVisibility(View.GONE);
                        liner_chooseAnddelete.setVisibility(View.GONE);
                    }
                }




                break;
        }
    }

    public void edit() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.history_item, null);
        collection_edit = (TextView) getActivity().findViewById(R.id.collection_edit);
        collection_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = collection_edit.getText().toString();

                if (collection_edit.getText().equals("编辑")) {
                    for (int i = 0; i < hList.size(); i++) {
                        hList.get(i).setVisibility(true);
                    }
                    liner_chooseAnddelete.setVisibility(View.VISIBLE);
                    collection_edit.setText("取消");
                    collectionAdapterRecy.notifyDataSetChanged();
                } else {
                    for (int i = 0; i < hList.size(); i++) {
                        hList.get(i).setVisibility(false);
                    }
                    for (int i = 0; i < hList.size(); i++) {
                        hList.get(i).setFlag(false);
                    }
                    liner_chooseAnddelete.setVisibility(View.GONE);
                    collection_edit.setText("编辑");
                    historyItemChooseBtn.setText("全选");
                    historyItemDeleteBtn.setText("删除");
                    number=0;
                    collectionAdapterRecy.notifyDataSetChanged();
                }

            }
        });
    }

}
