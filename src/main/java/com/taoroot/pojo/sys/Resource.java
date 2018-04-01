package com.taoroot.pojo.sys;

import com.taoroot.annotation.Index;
import com.taoroot.annotation.NoMapping;
import com.taoroot.annotation.Table;
import com.taoroot.pojo.BasePojo;
import com.taoroot.pojo.State;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: taoroot
 * @date: 2018/3/27
 * @description: 资源
 */
@Table("tb_resource")
public class Resource extends BasePojo {
    //	资源父id
    private Integer resourceParentId;
    //	资源名称
    private String resourceName;
    //	资源类型 0:菜单;1:功能
    private Integer resourceType;
    //	资源类别 web、app
    private String resourceKind;
    //	资源url
    private String resourceUrl;
    //	图标
    private String resourceIcon;
    //	资源排序
    @Index
    private int resourceIndex;
    //	是否隐藏
    private int resourceHide;
    //  层级
    private int resourcelevel;
    //  状态
    @NoMapping
    private State resourceState = new State();

    // 子节点
    @NoMapping
    private List<Resource> children = new ArrayList<>();

    public List<Resource> getChildren() {
        return children;
    }

    public Resource setChildren(List<Resource> children) {
        this.children = children;
        return this;
    }

    public Integer getResourceParentId() {
        return resourceParentId;
    }

    public Resource setResourceParentId(Integer resourceParentId) {
        this.resourceParentId = resourceParentId;
        return this;
    }

    public String getResourceName() {
        return resourceName;
    }

    public Resource setResourceName(String resourceName) {
        this.resourceName = resourceName;
        return this;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public Resource setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    public String getResourceKind() {
        return resourceKind;
    }

    public Resource setResourceKind(String resourceKind) {
        this.resourceKind = resourceKind;
        return this;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public Resource setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
        return this;
    }

    public String getResourceIcon() {
        return resourceIcon;
    }

    public Resource setResourceIcon(String resourceIcon) {
        this.resourceIcon = resourceIcon;
        return this;
    }

    public int getResourceIndex() {
        return resourceIndex;
    }

    public Resource setResourceIndex(int resourceIndex) {
        this.resourceIndex = resourceIndex;
        return this;
    }

    public int getResourceHide() {
        return resourceHide;
    }

    public Resource setResourceHide(int resourceHide) {
        this.resourceHide = resourceHide;
        return this;
    }

    public int getResourcelevel() {
        return resourcelevel;
    }

    public Resource setResourcelevel(int resourcelevel) {
        this.resourcelevel = resourcelevel;
        return this;
    }

    public State getResourceState() {
        return resourceState;
    }

    public Resource setResourceState(State resourceState) {
        this.resourceState = resourceState;
        return this;
    }
}
