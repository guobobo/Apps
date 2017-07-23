package com.example.iu.myapplication.model.entity;

/**
 * Created by iu on 2017/7/23.
 */

public class WorkBean {

    private String title;
    private String imageUrl;
    private String voideLength;
    private String dayTime;
    private boolean flag;
    private boolean visibility;

    public WorkBean() {

    }

    public WorkBean(String title, String imageUrl, String voideLength, String dayTime, boolean flag, boolean visibility) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.voideLength = voideLength;
        this.dayTime = dayTime;
        this.flag = flag;
        this.visibility = visibility;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVoideLength() {
        return voideLength;
    }

    public void setVoideLength(String voideLength) {
        this.voideLength = voideLength;
    }

    public String getDayTime() {
        return dayTime;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
}
