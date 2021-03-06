package com.xjh.springboot.pojo;

/*
    @Auther:四原色
    @Time:2021-07-29 19:37:00
*/
//菜单次分支导航对象
// id ：编号
// title ：菜单名称
// path ：菜单地址
// font :字体图标
//====================
//对应数据库表：submenu
// id ：编号 int
// title ：菜单名称 varchar(30)
// path ：菜单地址 varchar(50)
// mid  ：菜单主分支编号 int
//font :字体图标 varchar(50)
public class SubMenu {
    int id;
    String title;
    String path;
    String font;

    public SubMenu() {
    }

    public SubMenu(String title, String path,String font) {
        this.title = title;
        this.path = path;
        this.font = font;
    }

    public SubMenu(int id, String title, String path,String font) {
        this.id = id;
        this.title = title;
        this.path = path;
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

    @Override
    public String toString() {
        return "SubMenu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", font='" + font + '\'' +
                '}';
    }
}
