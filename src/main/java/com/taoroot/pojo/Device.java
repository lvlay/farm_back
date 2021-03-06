package com.taoroot.pojo;

import java.util.Date;

public class Device {
    private Integer id;

    private String did;

    private Integer model;

    private String token;

    private String name;

    private float lng;

    private float lat;

    private Date createTime;

    private Date updateTime;

    public Device(Integer id, String did, Integer model, String token, Date createTime, Date updateTime) {
        this.id = id;
        this.did = did;
        this.model = model;
        this.token = token;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Device(Integer id, String did, Integer model, String token, String name, Date createTime, Date updateTime) {
        this.id = id;
        this.did = did;
        this.model = model;
        this.token = token;
        this.name = name;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Device(Integer id, String did, Integer model, String token, String name, float lng, float lat, Date createTime, Date updateTime) {
        this.id = id;
        this.did = did;
        this.model = model;
        this.token = token;
        this.name = name;
        this.lng = lng;
        this.lat = lat;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Device() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did == null ? null : did.trim();
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public Device setName(String name) {
        this.name = name;
        return this;
    }

    public float getLng() {
        return lng;
    }

    public Device setLng(float lng) {
        this.lng = lng;
        return this;
    }

    public float getLat() {
        return lat;
    }

    public Device setLat(float lat) {
        this.lat = lat;
        return this;
    }
}