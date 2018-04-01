package com.taoroot.pojo.sys;

import com.taoroot.annotation.Table;

/**
 * @author: taoroot
 * @date: 2018/3/27
 * @description: 用户角色关联
 */
@Table(value = "tb_role_user")
public class RoleUser {
    // 用户id
    private String userId;
    // 角色id
    private String roleId;

    public String getUserId() {
        return userId;
    }

    public RoleUser setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getRoleId() {
        return roleId;
    }

    public RoleUser setRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }
}
