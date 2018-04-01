package com.taoroot.pojo.sys;

import com.taoroot.annotation.Table;
import com.taoroot.pojo.BasePojo;

/**
 * @author: taoroot
 * @date: 2018/3/27
 * @description: 用户
 */

@Table(value = "tb_user")
public class User extends BasePojo {
    private String userName;
    private String password;
    private String email;
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public User setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }
}