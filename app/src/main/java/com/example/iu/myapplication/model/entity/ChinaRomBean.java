package com.example.iu.myapplication.model.entity;


import android.os.Parcel;
import android.os.Parcelable;

public class ChinaRomBean implements Parcelable {
    private String title;
    private String url;
    private String type;
    private String order;

    public ChinaRomBean(String title, String url, String type, String order) {
        this.title = title;
        this.url = url;
        this.type = type;
        this.order = order;
    }

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

    protected ChinaRomBean(Parcel in) {
        this.title = in.readString();
        this.url = in.readString();
        this.type = in.readString();
        this.order = in.readString();
    }

    public static final Parcelable.Creator<ChinaRomBean> CREATOR = new Parcelable.Creator<ChinaRomBean>() {
        @Override
        public ChinaRomBean createFromParcel(Parcel source) {
            return new ChinaRomBean(source);
        }

        @Override
        public ChinaRomBean[] newArray(int size) {
            return new ChinaRomBean[size];
        }
    };
}
