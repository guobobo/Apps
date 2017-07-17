package com.example.iu.myapplication.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by dell on 2017/7/12.
 */

public class ChinaBean implements Parcelable {

    private List<TablistBean> tablist;
    private List<AlllistBean> alllist;

    public List<TablistBean> getTablist() {
        return tablist;
    }

    public void setTablist(List<TablistBean> tablist) {
        this.tablist = tablist;
    }

    public List<AlllistBean> getAlllist() {
        return alllist;
    }

    public void setAlllist(List<AlllistBean> alllist) {
        this.alllist = alllist;
    }

    public static class TablistBean implements Parcelable {
        /**
         * title : 八达岭
         * url : http://www.ipanda.com/kehuduan/liebiao/badaling/index.json
         * type :
         * order : 1
         */

        private String title;
        private String url;
        private String type;
        private String order;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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
            dest.writeString(this.url);
            dest.writeString(this.type);
            dest.writeString(this.order);
        }

        public TablistBean() {
        }

        protected TablistBean(Parcel in) {
            this.title = in.readString();
            this.url = in.readString();
            this.type = in.readString();
            this.order = in.readString();
        }

        public static final Parcelable.Creator<TablistBean> CREATOR = new Parcelable.Creator<TablistBean>() {
            @Override
            public TablistBean createFromParcel(Parcel source) {
                return new TablistBean(source);
            }

            @Override
            public TablistBean[] newArray(int size) {
                return new TablistBean[size];
            }
        };
    }

    public static class AlllistBean implements Parcelable {
        /**
         * title : 凤凰古城
         * url : http://www.ipanda.com/kehuduan/liebiao/fenghuanggucheng/index.json
         * type :
         * order : 1
         */

        private String title;
        private String url;
        private String type;
        private String order;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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
            dest.writeString(this.url);
            dest.writeString(this.type);
            dest.writeString(this.order);
        }

        public AlllistBean() {
        }

        protected AlllistBean(Parcel in) {
            this.title = in.readString();
            this.url = in.readString();
            this.type = in.readString();
            this.order = in.readString();
        }

        public static final Parcelable.Creator<AlllistBean> CREATOR = new Parcelable.Creator<AlllistBean>() {
            @Override
            public AlllistBean createFromParcel(Parcel source) {
                return new AlllistBean(source);
            }

            @Override
            public AlllistBean[] newArray(int size) {
                return new AlllistBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.tablist);
        dest.writeTypedList(this.alllist);
    }

    public ChinaBean() {
    }

    protected ChinaBean(Parcel in) {
        this.tablist = in.createTypedArrayList(TablistBean.CREATOR);
        this.alllist = in.createTypedArrayList(AlllistBean.CREATOR);
    }

    public static final Parcelable.Creator<ChinaBean> CREATOR = new Parcelable.Creator<ChinaBean>() {
        @Override
        public ChinaBean createFromParcel(Parcel source) {
            return new ChinaBean(source);
        }

        @Override
        public ChinaBean[] newArray(int size) {
            return new ChinaBean[size];
        }
    };
}
