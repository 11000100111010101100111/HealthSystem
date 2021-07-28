package com.xjh.springboot.pojo;

/*
*用户类实体
*
* 用户表：easyuser
*   |账号：id -> int(24)
*   |用户姓名：username -> varchar(50) 主键自动递增 字段非空
*   |登录密码：password -> varchar(30) 字段非空
*   |邮箱：email -> varchar(30) 字段非空
*   |角色类型:role -> varchar(20) 字段非空
*   |启用标识:state -> tinyint(3) 填充零 字段非空
*
*@Author 四原色
* 2021-07-28 19:5:00
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;
    private boolean state;

    public User() {
    }

    public User(int id, String username, String password, String email, String role, boolean state) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.state = state;
    }

    public User(String username, String password, String email, String role, boolean state) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", state=" + state +
                '}';
    }
}
