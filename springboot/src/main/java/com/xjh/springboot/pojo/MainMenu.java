package com.xjh.springboot.pojo;

import java.util.List;

/*
    @Auther:四原色
    @Time:2021-07-29 19:37:00
*/
//菜单主分支导航对象
// id ：编号
// title ：菜单名称
// path ：菜单地址
// sList ：次分支导航对象集合
// font :字体图标
//====================
//对应数据库表：mainmenu
// id ：编号 int
// title ：菜单名称 varchar(30)
// path ：菜单地址 varchar(50)
//font :字体图标 varchar(50)
public class MainMenu {
    int id;
    String title;
    String path;
    String font;
    List<SubMenu> sList;

    public MainMenu() {
    }

    public MainMenu(String title, String path,String font, List<SubMenu> sList) {
        this.title = title;
        this.path = path;
        this.sList = sList;
        this.font = font;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<SubMenu> getsList() {
        return sList;
    }

    public void setsList(List<SubMenu> sList) {
        this.sList = sList;
    }

    @Override
    public String toString() {
        return "MainMenu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", font='" + font + '\'' +
                ", sList=" + sList +
                '}';
    }
}
