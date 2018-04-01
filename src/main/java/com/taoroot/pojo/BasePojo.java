package com.taoroot.pojo;

import com.taoroot.annotation.Id;
import com.taoroot.annotation.LogicDelete;
import com.taoroot.annotation.NoMapping;

import java.util.Date;

/**
 * @author: taoroot
 * @date: 2018/3/26
 * @description: 基本字段
 */
public class BasePojo {
    @Id
    protected Integer id;

    //创建用户id
    protected Integer createUserId;

    //创建时间
    protected Date createTime;

    //更新时间
    protected Date updateTime = new Date();

    //标志位1:有效，0无效 默认1
    @LogicDelete
    protected Integer status = 0;

    public Integer getId() {
        return id;
    }

    public BasePojo setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public BasePojo setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BasePojo setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public BasePojo setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public BasePojo setStatus(Integer status) {
        this.status = status;
        return this;
    }
}
