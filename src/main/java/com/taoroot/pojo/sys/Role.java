package com.taoroot.pojo.sys;

import com.taoroot.annotation.Table;
import com.taoroot.pojo.BasePojo;

/**
 * @author: taoroot
 * @date: 2018/3/27
 * @description: 角色
 */
@Table(value = "tb_role")
public class Role extends BasePojo {
    // 角色名称
    private String roleName;
    // 角色描述
    private String roleDescription;

    public String getRoleName() {
        return roleName;
    }

    public Role setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }
}
