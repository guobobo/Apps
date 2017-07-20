package com.example.iu.myapplication.model.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by iu on 2017/7/19.
 */
@Entity
public class Work {
    @Id
    private Long id;

    @Property(nameInDb = "title")
    private String title;

    @Property(nameInDb = "data")
    private String data;

    @Property(nameInDb = "duration")
    private String duration;

    @Property(nameInDb = "image")
    private String image;

    @Property(nameInDb = "url")
    private String url;

    @Generated(hash = 1078099863)
    public Work(Long id, String title, String data, String duration, String image,
            String url) {
        this.id = id;
        this.title = title;
        this.data = data;
        this.duration = duration;
        this.image = image;
        this.url = url;
    }

    @Generated(hash = 572069219)
    public Work() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



}
