package com.example.iu.myapplication.module.home;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.iu.myapplication.App;
import com.example.iu.myapplication.R;
import com.example.iu.myapplication.base.BaseFragment;
import com.example.iu.myapplication.config.ACache;
import com.example.iu.myapplication.config.LogUtils;
import com.example.iu.myapplication.customize.HistoryUtils;
import com.example.iu.myapplication.model.entity.HomeBean;
import com.example.iu.myapplication.model.entity.Home_CCTV_Bean;
import com.example.iu.myapplication.model.entity.Home_China_Movie_Text;
import com.example.iu.myapplication.model.entity.Look_Down_Text;
import com.example.iu.myapplication.model.entity.UpdateBean;
import com.example.iu.myapplication.module.home.adapter.HomeFragmentAdapter;
import com.example.iu.myapplication.module.pandabroadcast.activity.BroadcastSpActivity;
import com.example.iu.myapplication.module.pandabroadcast.activity.BroadcastWebActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.analytics.MobclickAgent;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;

/**
 * Created by dell on 2017/7/12.
 */

public class HomeFragment extends BaseFragment implements HomeContarct.View ,HomeFragmentAdapter.x_Recy_Onclick {

    @Bind(R.id.home_xrecy)
    XRecyclerView homeXrecy;
    private ArrayList<Object> home_data_object = new ArrayList<>();
    private ArrayList<HomeBean.DataBean> list = new ArrayList<HomeBean.DataBean>();
    private static int versionCode;
    private HomeContarct.Presenter presenter;
    private HomeFragmentAdapter adapter;
    private String versionsUrl;
    private AlertDialog alertDialog;
    private  int total = 0;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {

        initData();
        getVersion();
        adapter = new HomeFragmentAdapter(home_data_object, getActivity(), list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());

        homeXrecy.setLayoutManager(manager);

        homeXrecy.setAdapter(adapter);
        homeXrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                adapter.notifyDataSetChanged();
                homeXrecy.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                Toast.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                homeXrecy.refreshComplete();
            }
        });
    }

    @Override
    public void loadDate() {
        presenter.start();

    }

    @Override
    public void showProgressDialog() {


    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void setResult(HomeBean homeBean) {

        home_data_object.clear();
        home_data_object.add(homeBean.getData().getBigImg().get(0));
        home_data_object.add(homeBean.getData().getArea());
        home_data_object.add(homeBean.getData().getPandaeye());
        home_data_object.add(homeBean.getData().getPandalive());
        home_data_object.add(homeBean.getData().getWalllive());
        home_data_object.add(homeBean.getData().getChinalive());
        home_data_object.add(homeBean.getData().getInteractive());
        home_data_object.add(homeBean.getData().getCctv());
        home_data_object.add(homeBean.getData().getList().get(0));

        list.clear();

        HomeBean.DataBean data = homeBean.getData();

        list.add(data);
        adapter.notifyDataSetChanged();




        adapter.setx_Recy_Onclick(this);
    }

    @Override
    public void setMessage(String msg) {
        LogUtils.MyLog("TAG","走没");
        ACache aCache = ACache.get(App.context);
        HomeBean homeBean = (HomeBean) aCache.getAsObject("HomeBean");
        if(homeBean!=null){
            list.add(homeBean.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setPresenter(HomeContarct.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void get_Ratation_Click(View v, HomeBean.DataBean.BigImgBean bigImgBean) {

        String order = bigImgBean.getOrder();

        int i = Integer.parseInt(order);

        HomeBean.DataBean dataBean = list.get(0);

        List<HomeBean.DataBean.BigImgBean> bigImg = dataBean.getBigImg();

        String url = bigImg.get(i - 1).getUrl();

        Intent intent = new Intent(getActivity(),BroadcastWebActivity.class);

        intent.putExtra("name",url);

        getActivity().startActivity(intent);
    }

    //精彩推荐的监听
    @Override
    public void get_wonderful_Click(HomeBean.DataBean.AreaBean.ListscrollBean home_data) {

        String pid = home_data.getPid();
        String title = home_data.getTitle();
        String image = home_data.getImage();
        String videoLength = home_data.getVideoLength();

        HistoryUtils.getInstance(getActivity()).instert(title,image,videoLength);

        thisTovideo(title,image,videoLength,pid);
    }

    @Override
    public void get_pandan_loog_Click(View look_view, HomeBean.DataBean.PandaeyeBean.ItemsBean itemsBean) {

        String pid = itemsBean.getPid();
        String title = itemsBean.getTitle();

        Intent intent = new Intent(getActivity(),BroadcastSpActivity.class);

        intent.putExtra("id",pid);
        intent.putExtra("title",title);

        //HistoryUtils.getInstance(getActivity()).instert();

        getActivity().startActivity(intent);

    }

    @Override
    public void get_pandan_loog_second_Click(View look_view, HomeBean.DataBean.PandaeyeBean.ItemsBean second_itemsBean) {

        String pid = second_itemsBean.getPid();
        String title = second_itemsBean.getTitle();

        Intent intent = new Intent(getActivity(),BroadcastSpActivity.class);

        intent.putExtra("id",pid);
        intent.putExtra("title",title);


        getActivity().startActivity(intent);

    }

    @Override
    public void get_pandan_look_down_Click(Look_Down_Text.ListBean look_down_text) {

        String pid = look_down_text.getPid();

        String videoLength = look_down_text.getVideoLength();

        String image = look_down_text.getImage();

        String title = look_down_text.getTitle();

        HistoryUtils.getInstance(getActivity()).instert(title,image,videoLength);

       thisTovideo(title,image,videoLength,pid);
    }

    @Override
    public void get_Panda_live_Click(HomeBean.DataBean.PandaliveBean pandalivebean) {

    }

    @Override
    public void get_great_live_Click(HomeBean.DataBean.WallliveBean listBeanX) {

    }

    @Override
    public void get_china_live_Click(HomeBean.DataBean.ChinaliveBean listBeanXX) {

    }

    @Override
    public void get_special_planning_Click(View v, HomeBean.DataBean.InteractiveBean.InteractiveoneBean interactiveoneBean) {
                //get_special_planning_Click
        Intent intent = new Intent(getActivity(),BroadcastWebActivity.class);

        intent.putExtra("name",interactiveoneBean.getUrl());

        getActivity().startActivity(intent);
    }

    @Override
    public void get_cctv_live_Click(Home_CCTV_Bean.ListBean listBean) {

    }

    @Override
    public void get_movie_live_Click(Home_China_Movie_Text.ListBean listBean) {

        String pid = listBean.getPid();

        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String format = sdf.format(date);

        String title = listBean.getTitle();

        String videoLength = listBean.getVideoLength();

        String image = listBean.getImage();

        HistoryUtils.getInstance(getActivity()).instert(title,image,videoLength);

        thisTovideo(title,image,videoLength,pid);

    }

    private void thisTovideo(String title , String image , String videoLength ,String pid){

        Intent intent = new Intent(getActivity(), BroadcastSpActivity.class);

        intent.putExtra("title",title);

        intent.putExtra("image",image);

        intent.putExtra("duration",videoLength);

        intent.putExtra("id",pid);

        getActivity().startActivity(intent);

    }

    //获取当前版本
    protected void initData() {
        getAppVersionName(getActivity());
    }

    public static String getAppVersionName(Context context) {
        String versionName = "";

        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            //versioncode = pi.versionCode;
            versionCode = pi.versionCode;
            LogUtils.MyLog("aaa", versionCode + "");
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            LogUtils.MyLog("aaa", versionName);
        }
        return versionName;

    }

    //发送网络请求获取最新版本号
    public void getVersion() {
        presenter.getversion();
    }

    @Override
    public void getVersion(UpdateBean updateBean) {
        String versionsNum = updateBean.getData().getVersionsNum();
        versionsUrl = updateBean.getData().getVersionsUrl();
        int versionsInt = Integer.parseInt(versionsNum);
        if (versionCode < versionsInt) {
            showDialogUpdate();
        } else {
            Toast.makeText(getActivity(), "已经是最新版本", Toast.LENGTH_LONG).show();
        }

    }

  //提示更新
    private void showDialogUpdate() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // 设置提示框的标题
        builder.setTitle("版本升级").
                // 设置要显示的信息
                        setMessage("发现新版本  修复了已知的BUG").
                // 设置确定按钮
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(MainActivity.this, "选择确定哦", 0).show();
                        dialog.dismiss();
                        loadNewVersionProgress();//下载最新的版本程序

                    }
                }).

                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("取消", null);

        // 生产对话框
        alertDialog = builder.create();
        // 显示对话框
        alertDialog.show();


    }


//     下载新版本程序
    private void loadNewVersionProgress() {
        final String uri = versionsUrl;
        final ProgressDialog pd;    //进度条对话框
        pd = new ProgressDialog(getActivity());
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.show();
        //启动子线程下载任务
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(uri, pd);
                    sleep(3000);
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    //下载apk失败
                    LogUtils.MyLog("abc", "下载失败");
//                    Toast.makeText(getActivity(), "下载新版本失败", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }.start();
    }

    //从服务器获取apk文件的代码

    public File getFileFromServer(String uri, final ProgressDialog pd) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            pd.setMax(conn.getContentLength()/100);
            InputStream is = conn.getInputStream();
            long time = System.currentTimeMillis();//当前时间的毫秒数
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), time + "updata.apk");
            if (!file.exists())
                file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;

            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                //获取当前下载量
                pd.setProgress(total/100);
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            return null;
        }
    }

   //安装Apk
    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(getActivity());
        MobclickAgent.onPageStart("HomeFragment");//统计时长
    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(getActivity());
        MobclickAgent.onPageStart("HomeFragment");
    }

}
