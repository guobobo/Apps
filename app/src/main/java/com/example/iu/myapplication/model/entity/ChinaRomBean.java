package com.example.iu.myapplication.model.entity;


import java.io.Serializable;

public class ChinaRomBean implements Serializable {
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
}
