package com.jinzunyue.share.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("rc_privilege")
public class Privilege {
    @TableField("role_id")
    private Integer roleId;
    @TableField("menu_id")
    private String menuId;

    @Override
    public String toString() {
        return "Privilege{" +
                "roleId=" + roleId +
                ", menuId='" + menuId + '\'' +
                '}';
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
