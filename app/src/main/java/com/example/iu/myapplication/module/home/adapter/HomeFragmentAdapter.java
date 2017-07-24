package com.example.iu.myapplication.module.home.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iu.myapplication.app.App;
import com.example.iu.myapplication.R;
import com.example.iu.myapplication.model.entity.HomeBean;
import com.example.iu.myapplication.model.entity.Home_CCTV_Bean;
import com.example.iu.myapplication.model.entity.Home_China_Movie_Text;
import com.example.iu.myapplication.model.entity.Look_Down_Text;
import com.example.iu.myapplication.net.OkhttpUtils;
import com.example.iu.myapplication.net.callback.MyNetWorkCallBack;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by dell on 2017/7/15.
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter implements ViewPager.OnPageChangeListener , Home_Wonderful_Adapter.Wonder_Onclick {


    private HomeBean.DataBean.AreaBean areaBean;

    public interface x_Recy_Onclick {

//        轮播图监听
        void get_Ratation_Click(View v, HomeBean.DataBean.BigImgBean bigImgBean);
        //        精彩推荐  监听的方法
        void get_wonderful_Click(HomeBean.DataBean.AreaBean.ListscrollBean home_data);
        //        熊猫观察（新生） 监听的方法
        void get_pandan_loog_Click(View look_view, HomeBean.DataBean.PandaeyeBean.ItemsBean itemsBean);
        //       熊猫观察（趣闻） 监听的方法
        void get_pandan_loog_second_Click(View look_view, HomeBean.DataBean.PandaeyeBean.ItemsBean second_itemsBean);
        //        熊猫观察（listview）监听
        void get_pandan_look_down_Click(Look_Down_Text.ListBean look_down_text);
        //熊猫直播的监听
        void get_Panda_live_Click(HomeBean.DataBean.PandaliveBean pandalivebean);
        //        //        长城直播的监听
        void get_great_live_Click(HomeBean.DataBean.WallliveBean listBeanX);
        //
//        //        直播中国监听
        void get_china_live_Click(HomeBean.DataBean.ChinaliveBean listBeanXX);
//        //        特别策划监听
        void get_special_planning_Click(View v, HomeBean.DataBean.InteractiveBean.InteractiveoneBean interactiveoneBean);
//        //        CCTV 点击事件监听
        void get_cctv_live_Click(Home_CCTV_Bean.ListBean listBean);
//        //        公映中国监听
        void get_movie_live_Click(Home_China_Movie_Text.ListBean listBean);

    }

    private x_Recy_Onclick recy_onclick;

    private ArrayList<Object> list;
    private Context context;


    public void setx_Recy_Onclick(x_Recy_Onclick recy_onclick){

        this.recy_onclick = recy_onclick;
    }

    //    存放滚动轮播的 集合
    private ArrayList<View> rotation_array = new ArrayList<>();
    //    滚动轮播的图片
    private ImageView imag;
    //    滚动轮播图片上的文字
    private TextView textView;
    //    滚动轮播的ViewPage
    private ViewPager viewPager;
    //    Viewpage 的 适配器
    private Home_page_Rotation home_page_rotation;
    //    时间2类
    private Timer timer;
    //    轮播的四个小点
    LinearLayout point_ratio = null;
    private boolean time_flg = false;
    private ArrayList<HomeBean.DataBean> home_data;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 300:
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);

                    break;

            }

        }
    };


    public HomeFragmentAdapter(ArrayList<Object> list, Context context, ArrayList<HomeBean.DataBean> home_data) {
        this.list = list;
        this.context = context;
        this.home_data = home_data;
    }

    //轮播图
    public static final int ratation_type = 1;
    public static final int wonderful_type = 2;
    public static final int panda_look_type = 3;
    public static final int panda_live_type = 4;
    public static final int great_wall_type = 5;
    public static final int lice_china_type = 6;
    public static final int special_planning_type = 7;
    public static final int cctv_type = 8;
    public static final int movie_china_type = 9;
    private ArrayList<Look_Down_Text.ListBean> Look_Down_Array = new ArrayList();
    private ArrayList<Home_CCTV_Bean.ListBean> cctv_Array = new ArrayList<Home_CCTV_Bean.ListBean>();
    private ArrayList<Home_China_Movie_Text.ListBean> movie_Array = new ArrayList<>();

    //    加载不同的布局
    @Override
    public int getItemViewType(int position) {
        Object o = list.get(position);
//轮播图
        if (o instanceof HomeBean.DataBean.BigImgBean) {
            return ratation_type;
        }
//        精彩推荐
        if (o instanceof HomeBean.DataBean.AreaBean) {
            return wonderful_type;
        }
//熊猫观察
        if (o instanceof HomeBean.DataBean.PandaeyeBean) {
            return panda_look_type;
        }
//熊猫直播
        if (o instanceof HomeBean.DataBean.PandaliveBean) {
            return panda_live_type;
        }
//长城直播
        if (o instanceof HomeBean.DataBean.WallliveBean) {
            return great_wall_type;
        }
//直播中国
        if (o instanceof HomeBean.DataBean.ChinaliveBean) {
            return lice_china_type;
        }
//特别策划
        if (o instanceof HomeBean.DataBean.InteractiveBean) {
            return special_planning_type;
        }
//CCTV
        if (o instanceof HomeBean.DataBean.CctvBean) {
            return cctv_type;
        }
//公映中国
        if (o instanceof HomeBean.DataBean.ListBeanXXX) {
            return movie_china_type;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            //            轮播图
            case 1:

                View raio_view = LayoutInflater.from(context).inflate(R.layout.login_home_rotation, null);

                return new Ration_viewHolder(raio_view);

            //            精彩推荐
            case 2:

                View wonder_view = LayoutInflater.from(context).inflate(R.layout.homefragment_recy_area, null);

                return new Wondelful_viewHolder(wonder_view);
            //            熊猫观察
            case 3:

                View pande_look_view = LayoutInflater.from(context).inflate(R.layout.homefragment_recy_pandaeye, null);

                return new Panda_look_viewHolder(pande_look_view);

            //            熊猫直播
            case 4:
                View panda_live_view = LayoutInflater.from(context).inflate(R.layout.homefragment_recy_live, null);

                return new Panda_live_viewHolder(panda_live_view);
            //            长城直播
            case 5:
                View great_wall_view = LayoutInflater.from(context).inflate(R.layout.homefragment_recy_wall, null);

                return new Great_wall_viewHolder(great_wall_view);
            //            直播中国
            case 6:
                View live_china_view = LayoutInflater.from(context).inflate(R.layout.homefragment_recy_china, null);

                return new Live_china_viewHolder(live_china_view);
            //            特别策划
            case 7:
                View special_planning_view = LayoutInflater.from(context).inflate(R.layout.homefragment_recy_plan, null);

                return new Simping_viewHolder(special_planning_view);
            //            CCTV
            case 8:
                View cctv_view = LayoutInflater.from(context).inflate(R.layout.homefragment_recy_cctv, null);

                return new Cctv_viewHolder(cctv_view);
            //            光影中国
            case 9:
                View movie_china_view = LayoutInflater.from(context).inflate(R.layout.homefragment_recy_light, null);

                return new Movie_china_viewHolder(movie_china_view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        Object o = list.get(position);
        switch (getItemViewType(position)) {

            //轮播图
            case ratation_type:

                final Ration_viewHolder ration_view = (Ration_viewHolder) holder;

                HomeBean.DataBean.BigImgBean bigImgBean = (HomeBean.DataBean.BigImgBean) o;

                login_home_rotation(ration_view);

                break;

            //        精彩推荐的数据
            case wonderful_type:

                Wondelful_viewHolder viewHolder = (Wondelful_viewHolder) holder;

                areaBean = (HomeBean.DataBean.AreaBean) o;

                String image = areaBean.getImage();

                Glide.with(context).load(image).into(viewHolder.wonderful_image);

                List<HomeBean.DataBean.AreaBean.ListscrollBean> listscroll = areaBean.getListscroll();

                Home_Wonderful_Adapter adapter = new Home_Wonderful_Adapter(context,listscroll);

                LinearLayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);

                viewHolder.wonderful_recycel.setLayoutManager(manager);

                viewHolder.wonderful_recycel.setAdapter(adapter);

                adapter.Wonder_setOnclick(this);

                adapter.notifyDataSetChanged();

                break;
//熊猫观察
            case panda_look_type:

                final Panda_look_viewHolder look_viewHolder = (Panda_look_viewHolder) holder;

                final HomeBean.DataBean.PandaeyeBean pandaeyeBean = (HomeBean.DataBean.PandaeyeBean) o;

                Glide.with(context).load(pandaeyeBean.getPandaeyelogo()).into(look_viewHolder.eye_logeimage);

                look_viewHolder.eye_newtext.setText(pandaeyeBean.getItems().get(0).getTitle());

                //新生条目的监听
                look_viewHolder.eye_newtext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recy_onclick.get_pandan_loog_Click(v,pandaeyeBean.getItems().get(0));
                    }
                });

                look_viewHolder.eye_interestingtext.setText(pandaeyeBean.getItems().get(1).getTitle());

                //趣闻条目的监听
                look_viewHolder.eye_interestingtext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recy_onclick.get_pandan_loog_second_Click(v,pandaeyeBean.getItems().get(1));
                    }
                });

                OkhttpUtils.getInstance().get(pandaeyeBean.getPandaeyelist(), null, new MyNetWorkCallBack<Look_Down_Text>() {
                    @Override
                    public void onSuccess(Look_Down_Text look_down_text) {

                        Look_Down_Array.clear();

                        List<Look_Down_Text.ListBean> list = look_down_text.getList();

                        Look_Down_Array.addAll(list);

                        App.context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                PandaEyeListViewAdapter listViewAdapter = new PandaEyeListViewAdapter(Look_Down_Array, context);

                                look_viewHolder.listView.setAdapter(listViewAdapter);

                                listViewAdapter.notifyDataSetChanged();

                                look_viewHolder.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        recy_onclick.get_pandan_look_down_Click(Look_Down_Array.get(position));
                                    }
                                });
                            }
                        });

                    }

                    @Override
                    public void onError(String msg) {

                    }
                });


                break;
            //熊猫直播
            case panda_live_type:

                final HomeBean.DataBean.PandaliveBean pandaliveBean = (HomeBean.DataBean.PandaliveBean) o;
                Panda_live_viewHolder live_holder = (Panda_live_viewHolder) holder;
                GridLayoutManager mManager = new GridLayoutManager(context, 3);
                live_holder.live_show_recy.setLayoutManager(mManager);
                ArrayList<HomeBean.DataBean.PandaliveBean.ListBeanX> list = (ArrayList<HomeBean.DataBean.PandaliveBean.ListBeanX>) pandaliveBean.getList();
                Home_Live_Adapter home_live_adapter = new Home_Live_Adapter(list,context);
                live_holder.live_show_recy.setAdapter(home_live_adapter);
                break;
//长城直播
            case great_wall_type:
                final HomeBean.DataBean.WallliveBean wallliveBean = (HomeBean.DataBean.WallliveBean) o;
                Great_wall_viewHolder wall_viewHolder = (Great_wall_viewHolder) holder;
                GridLayoutManager manager_great = new GridLayoutManager(context, 3);
                wall_viewHolder.Great_Wall_recycle.setLayoutManager(manager_great);
                ArrayList<HomeBean.DataBean.WallliveBean.ListBeanXX> list1 = (ArrayList<HomeBean.DataBean.WallliveBean.ListBeanXX>) wallliveBean.getList();
                Home_Wall_Adapter home_wall_adapter = new Home_Wall_Adapter(list1,context);
                wall_viewHolder.Great_Wall_recycle.setAdapter(home_wall_adapter);
                break;
            //直播中国
            case lice_china_type:
                final HomeBean.DataBean.ChinaliveBean chinaliveBean = (HomeBean.DataBean.ChinaliveBean) o;
                Live_china_viewHolder china_viewHolder = (Live_china_viewHolder) holder;
                GridLayoutManager manager_china = new GridLayoutManager(context, 3);
                china_viewHolder.Live_China_Recycle.setLayoutManager(manager_china);

                ArrayList<HomeBean.DataBean.ChinaliveBean.ListBean> list2 = (ArrayList<HomeBean.DataBean.ChinaliveBean.ListBean>) chinaliveBean.getList();

                Home_China_Adapter home_china_adapter = new Home_China_Adapter(list2, context);

                china_viewHolder.Live_China_Recycle.setAdapter(home_china_adapter);

                break;
//特别推荐
            case special_planning_type:
                final HomeBean.DataBean.InteractiveBean interactiveBean = (HomeBean.DataBean.InteractiveBean) o;
                Simping_viewHolder sim_viewHolder = (Simping_viewHolder) holder;
                sim_viewHolder.Special_planning_title.setText(interactiveBean.getInteractiveone().get(0).getTitle());
                Glide.with(context).load(interactiveBean.getInteractiveone().get(0).getImage()).into(sim_viewHolder.Special_planning_Imagee);
                sim_viewHolder.Special_planning_Imagee.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recy_onclick.get_special_planning_Click(v,interactiveBean.getInteractiveone().get(0));
                    }
                });
                break;

            case cctv_type:
                final Cctv_viewHolder cctv_viewholder = (Cctv_viewHolder) holder;
//       设置CCTV里面的数据
                HomeBean.DataBean.CctvBean cctvBean = (HomeBean.DataBean.CctvBean) o;

                OkhttpUtils.getInstance().get(cctvBean.getListurl(), null, new MyNetWorkCallBack<Home_CCTV_Bean>() {
                    @Override
                    public void onSuccess(Home_CCTV_Bean home_cctv_bean) {

                        cctv_Array.clear();

                        ArrayList<Home_CCTV_Bean.ListBean> list3 = (ArrayList<Home_CCTV_Bean.ListBean>) home_cctv_bean.getList();

                        cctv_Array.addAll(list3);

                        App.context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                GridLayoutManager manager_cctv = new GridLayoutManager(context, 2);

                                cctv_viewholder.CCTV_recycle.setLayoutManager(manager_cctv);

                                Home_CCTV_Adapter home_cctv_adapter = new Home_CCTV_Adapter(cctv_Array, context);

                                cctv_viewholder.CCTV_recycle.setAdapter(home_cctv_adapter);

                                home_cctv_adapter.notifyDataSetChanged();
                            }
                        });
                    }

                    @Override
                    public void onError(String msg) {

                    }
                });

                break;

            case movie_china_type:
                final Movie_china_viewHolder chian_viewholder = (Movie_china_viewHolder) holder;
////     设置光影中国的  数据

                HomeBean.DataBean.ListBeanXXX listBeanXXX = (HomeBean.DataBean.ListBeanXXX) o;

                OkhttpUtils.getInstance().get(listBeanXXX.getListUrl(), null, new MyNetWorkCallBack<Home_China_Movie_Text>() {
                    @Override
                    public void onSuccess(Home_China_Movie_Text home_china_movie_text) {

                        movie_Array.clear();

                        ArrayList<Home_China_Movie_Text.ListBean> list4 = (ArrayList<Home_China_Movie_Text.ListBean>) home_china_movie_text.getList();

                        movie_Array.addAll(list4);

                        App.context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Home_Light_Adapter home_light_adapter = new Home_Light_Adapter(movie_Array, context);

                                chian_viewholder.china_movie_list.setAdapter(home_light_adapter);

                                chian_viewholder.china_movie_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        recy_onclick.get_movie_live_Click(movie_Array.get(position));
                                    }
                                });
                            }
                        });

                    }

                    @Override
                    public void onError(String msg) {

                    }
                });

                break;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //    写  9  个ViewHolder
    //轮播图
    class Ration_viewHolder extends RecyclerView.ViewHolder {

        ViewPager viewPager;
        LinearLayout linearLayout;

        public Ration_viewHolder(View itemView) {
            super(itemView);

            viewPager = (ViewPager) itemView.findViewById(R.id.my_login_home_rotation);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.my_login_home_rotation_point);
        }
    }

    //精彩推荐
    class Wondelful_viewHolder extends RecyclerView.ViewHolder {

        private RecyclerView wonderful_recycel;
        private ImageView wonderful_image;

        public Wondelful_viewHolder(View itemView) {
            super(itemView);
            wonderful_recycel = (RecyclerView) itemView.findViewById(R.id.area_recy);
            wonderful_image = (ImageView) itemView.findViewById(R.id.area_imge);
        }
    }
//熊猫观察
    class Panda_look_viewHolder extends RecyclerView.ViewHolder {

        ImageView eye_logeimage;
        TextView eye_newtext;
        TextView eye_interestingtext;
        ListView listView;

        public Panda_look_viewHolder(View itemView) {
            super(itemView);

            eye_logeimage  = (ImageView) itemView.findViewById(R.id.eye_logeimage);
            eye_newtext = (TextView) itemView.findViewById(R.id.eye_newtext);
            eye_interestingtext = (TextView) itemView.findViewById(R.id.eye_interestingtext);
            listView = (ListView) itemView.findViewById(R.id.pandaeye_listview);
        }
    }
//熊猫直播
    class Panda_live_viewHolder extends RecyclerView.ViewHolder {
        private RecyclerView live_show_recy;


        public Panda_live_viewHolder(View itemView) {
            super(itemView);
            live_show_recy = (RecyclerView) itemView.findViewById(R.id.live_show_recy);
        }
    }

    //长城直播
    class Great_wall_viewHolder extends RecyclerView.ViewHolder {
        private RecyclerView Great_Wall_recycle;

        public Great_wall_viewHolder(View itemView) {
            super(itemView);

          Great_Wall_recycle = (RecyclerView) itemView.findViewById(R.id.wall_recy);
        }
    }

    //直播中国
    class Live_china_viewHolder extends RecyclerView.ViewHolder {
        private RecyclerView Live_China_Recycle;

        public Live_china_viewHolder(View itemView) {
            super(itemView);

            Live_China_Recycle = (RecyclerView) itemView.findViewById(R.id.china_recy);
        }
    }

    //特别策划
    class Simping_viewHolder extends RecyclerView.ViewHolder {

        private TextView Special_planning_title;
        private ImageView Special_planning_Imagee;

        public Simping_viewHolder(View itemView) {
            super(itemView);

            Special_planning_Imagee = (ImageView) itemView.findViewById(R.id.plan_image);
            Special_planning_title = (TextView) itemView.findViewById(R.id.plan_text);
        }
    }

    //CCTV
    class Cctv_viewHolder extends RecyclerView.ViewHolder {
        private RecyclerView CCTV_recycle;

        public Cctv_viewHolder(View itemView) {
            super(itemView);
            CCTV_recycle = (RecyclerView) itemView.findViewById(R.id.cctv_recy);
        }
    }

    class Movie_china_viewHolder extends RecyclerView.ViewHolder {
        private ListView china_movie_list;

        public Movie_china_viewHolder(View itemView) {
            super(itemView);
            china_movie_list = (ListView) itemView.findViewById(R.id.light_listview);
        }
    }

    //    //    设置轮播图的方法
    public void login_home_rotation(Ration_viewHolder my_view) {
        point_ratio = (LinearLayout) my_view.itemView.findViewById(R.id.my_login_home_rotation_point);
        rotation_array.clear();
        point_ratio.removeAllViews();
        for (int i = 0; i < home_data.get(0).getBigImg().size(); i++) {

            View page_item = LayoutInflater.from(context).inflate(R.layout.login_home_rotation_item, null);
//            轮播图的图片
            imag = (ImageView) page_item.findViewById(R.id.home_rotation_item_imag);
//            轮播图的文字
            textView = (TextView) page_item.findViewById(R.id.home_rotation_item_text);
            textView.setText(home_data.get(0).getBigImg().get(i).getTitle());
            Glide.with(context).load(home_data.get(0).getBigImg().get(i).getImage()).into(imag);
            rotation_array.add(page_item);
            final int finalI = i;
            //轮播图图片的监听
            imag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    recy_onclick.get_Ratation_Click(v, home_data.get(0).getBigImg().get(finalI));

                }
            });


//            储存俩个点
            View v_point = new View(context);
            v_point.setBackgroundResource(R.drawable.rotation_point_blue);
            LinearLayout.LayoutParams LP = new LinearLayout.LayoutParams(20, 20);
            LP.setMargins(20, 0, 0, 0);
            point_ratio.addView(v_point, LP);

        }

        viewPager = (ViewPager) my_view.itemView.findViewById(R.id.my_login_home_rotation);
        point_ratio.getChildAt(0).setBackgroundResource(
                R.drawable.rotation_point_write);
        home_page_rotation = new Home_page_Rotation(rotation_array);

        viewPager.setAdapter(home_page_rotation);
        viewPager.setOnPageChangeListener(this);
        viewPager.setCurrentItem(100000000);

        if (time_flg == false) {

            timer = new Timer();
            timer.schedule(task, 4000, 4000);

            time_flg = true;
        }
    }

    private TimerTask task = new TimerTask() {
        @Override
        public void run() {

            handler.sendEmptyMessage(300);

        }
    };

    //    轮播图的 点击事件
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int newPosition = position % rotation_array.size();
        for (int i = 0; i < home_data.get(0).getBigImg().size(); i++) {
            if (i == newPosition) {
                // 就将i对应的点设置为选中状态，其他的点都设置成未选中状态
                point_ratio.getChildAt(i).setBackgroundResource(
                        R.drawable.rotation_point_blue);
            } else {
                point_ratio.getChildAt(i).setBackgroundResource(
                        R.drawable.rotation_point_write);
            }

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void Wonder_getOnclick(View view, int postion) {

        recy_onclick.get_wonderful_Click(areaBean.getListscroll().get(postion));

    }
}
