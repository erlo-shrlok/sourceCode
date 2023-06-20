package com.jinzunyue.share.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

@TableName("rc_menu")
public class Menu {
    private String id;
    private String code;
    @TableField("p_code")
    private String pCode;
    @TableField("p_id")
    private String pId;
    private String name;
    private String url;
    @TableField("is_menu")
    private Integer isMenu;
    private Integer level;
    private Integer sort;
    private Integer status;
    @TableField("create_time")
    private String createTime;
    @TableField("update_time")
    private String updateTime;

    private List<Menu> child;

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", pCode='" + pCode + '\'' +
                ", pId='" + pId + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", isMenu=" + isMenu +
                ", level=" + level +
                ", sort=" + sort +
                ", status=" + status +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", child=" + child +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Integer isMenu) {
        this.isMenu = isMenu;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<Menu> getChild() {
        return child;
    }

    public void setChild(List<Menu> child) {
        this.child = child;
    }
}
