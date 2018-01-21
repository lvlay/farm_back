package com.taoroot.vo;

import java.util.Date;

/**
 * @author: taoroot
 * @date: 2018/1/13
 * @description:
 */
public class UserVo {

    private String username;

    private String email;

    private String phone;

    private Integer role;

    private Date createTime;

    private Date updateTime;



    public UserVo setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserVo setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserVo setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserVo setRole(Integer role) {
        this.role = role;
        return this;
    }

    public UserVo setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public UserVo setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getRole() {
        return role;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }
}
