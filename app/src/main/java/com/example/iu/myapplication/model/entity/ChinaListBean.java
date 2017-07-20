package com.example.iu.myapplication.model.entity;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ChinaListBean implements Parcelable {

    private List<LiveBean> live;

    public List<LiveBean> getLive() {
        return live;
    }

    public void setLive(List<LiveBean> live) {
        this.live = live;
    }

    public static class LiveBean implements Parcelable {
        /**
         * title : 八达岭南四楼
         * brief : 八达岭长城位于北京市西北六十公里处，被评为中国旅游胜地四十家之首和北京旅游“世界之最”。因其机构严谨科学，虽经历五百多年的历史风烟，至今仍巍然屹立，足以说明古代汉族劳动人民在建筑科学和艺术上的卓越才能。
         * image : http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/12/28/1451290603994_440.jpg
         * id : bgws4
         * order : 1
         */

        private String title;
        private String brief;
        private String image;
        private String id;
        private String order;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.title);
            dest.writeString(this.brief);
            dest.writeString(this.image);
            dest.writeString(this.id);
            dest.writeString(this.order);
        }

        public LiveBean() {
        }

        protected LiveBean(Parcel in) {
            this.title = in.readString();
            this.brief = in.readString();
            this.image = in.readString();
            this.id = in.readString();
            this.order = in.readString();
        }

        public static final Creator<LiveBean> CREATOR = new Creator<LiveBean>() {
            @Override
            public LiveBean createFromParcel(Parcel source) {
                return new LiveBean(source);
            }

            @Override
            public LiveBean[] newArray(int size) {
                return new LiveBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.live);
    }

    public ChinaListBean() {
    }

    protected ChinaListBean(Parcel in) {
        this.live = new ArrayList<LiveBean>();
        in.readList(this.live, LiveBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<ChinaListBean> CREATOR = new Parcelable.Creator<ChinaListBean>() {
        @Override
        public ChinaListBean createFromParcel(Parcel source) {
            return new ChinaListBean(source);
        }

        @Override
        public ChinaListBean[] newArray(int size) {
            return new ChinaListBean[size];
        }
    };
}
