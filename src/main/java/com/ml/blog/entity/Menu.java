package com.ml.blog.entity;

public class Menu {

    private Integer menuId;

    private String menuName;

    private String url;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Menu() {
    }

    public Menu(Integer menuId, String menuName, String url) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.url = url;
    }

}