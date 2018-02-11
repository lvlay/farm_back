package com.taoroot.pojo;

public class Greenhouse {
    private int id;

    private String name;

    private Integer userId;

    public Greenhouse(int id, String name, Integer userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

    public Greenhouse() {
        super();
    }

    public Greenhouse(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Greenhouse setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}