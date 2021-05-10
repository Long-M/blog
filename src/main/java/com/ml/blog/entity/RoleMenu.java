package com.ml.blog.entity;

public class RoleMenu {

    private Integer roleMenuId;

    private Integer roleId;

    private Integer menuId;

    public Integer getRoleMenuId() {
        return roleMenuId;
    }

    public void setRoleMenuId(Integer roleMenuId) {
        this.roleMenuId = roleMenuId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public RoleMenu() {
    }

    public RoleMenu(Integer roleMenuId, Integer roleId, Integer menuId) {
        this.roleMenuId = roleMenuId;
        this.roleId = roleId;
        this.menuId = menuId;
    }

}