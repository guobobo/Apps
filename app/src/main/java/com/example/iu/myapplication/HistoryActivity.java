package com.example.iu.myapplication;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.iu.myapplication.base.BaseActivity;
import com.example.iu.myapplication.config.LogUtils;
import com.example.iu.myapplication.model.dao.DaoMaster;
import com.example.iu.myapplication.model.dao.DaoSession;
import com.example.iu.myapplication.model.dao.History;
import com.example.iu.myapplication.model.dao.HistoryDao;
import com.example.iu.myapplication.model.dao.MyHistoryHelper;
import com.example.iu.myapplication.model.entity.HistoryBean;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class HistoryActivity extends BaseActivity {


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

    private LinkedList<History> mList = new LinkedList<History>();
    private ArrayList<HistoryBean>  hList=new ArrayList<>();
    private HistoryAdapter adapter;
    private boolean flag = false;
    private int number = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_history;
    }

    @Override
    public void initView() {
        select();
            historyEditBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
                }
            });

        historyItemChooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
            }
        });

        historyListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HistoryBean historyBean = hList.get(position);

                boolean flag = historyBean.isFlag();

                LogUtils.MyLog("TAG",flag+"当前flag");

                if(historyBean.isFlag()==false){
                    historyBean.setFlag(true);
                    number++;
                    historyItemDeleteBtn.setText("删除"+"("+number+")");
                }else {
                    number--;
                    historyItemDeleteBtn.setText("删除"+"("+number+")");
                    historyBean.setFlag(false);
                }
            }
        });
    }

    @OnClick({R.id.history_backImage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.history_backImage:
                finish();
                break;
        }
    }

    //查询数据库同时给listview加载数据。。
    public void select() {

        DaoMaster master = new DaoMaster(MyHistoryHelper.gethelper(HistoryActivity.this).getR());

        DaoSession session = master.newSession();

        HistoryDao historyDao = session.getHistoryDao();

        QueryBuilder<History> builder = historyDao.queryBuilder();

        List<History> list = builder.list();
        hList.clear();
        for(int i = 0; i < list.size(); i++) {
            hList.add(new HistoryBean(list.get(i).getTitle(),list.get(i).getImageUrl(),list.get(i).getVoideLength(),list.get(i).getDayTime(),false,false));
        }

        mList.clear();
        adapter = new HistoryAdapter(hList, this,flag);
        historyListview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void deleteAll() {


        DaoMaster master = new DaoMaster(MyHistoryHelper.gethelper(HistoryActivity.this).getW());

        DaoSession session = master.newSession();

        HistoryDao historyDao = session.getHistoryDao();

        historyDao.deleteAll();
    }


    public void updata(History history){

        DaoMaster master = new DaoMaster(MyHistoryHelper.gethelper(this).getW());

        DaoSession session = master.newSession();

        HistoryDao historyDao = session.getHistoryDao();

        historyDao.update(history);
    }

}
