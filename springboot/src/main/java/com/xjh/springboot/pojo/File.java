package com.xjh.springboot.pojo;

import java.sql.Date;

public class File {
    Long id;//文件编号
    String name;//文件名称
    String path;//文件地址
    String description;//文件描述
    int uid;//文件上传者id
    Date uploadtime;//文件上传时间
    long downloadtimes;//文件被下载次数
    long browsetimes;//文件浏览次数

    public File() {
    }

    public File(String name, String path, String description, int uid, Date uploadtime, long downloadtimes, long browsetimes) {
        this.name = name;
        this.path = path;
        this.description = description;
        this.uid = uid;
        this.uploadtime = uploadtime;
        this.downloadtimes = downloadtimes;
        this.browsetimes = browsetimes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public long getDownloadtimes() {
        return downloadtimes;
    }

    public void setDownloadtimes(long downloadtimes) {
        this.downloadtimes = downloadtimes;
    }

    public long getBrowsetimes() {
        return browsetimes;
    }

    public void setBrowsetimes(long browsetimes) {
        this.browsetimes = browsetimes;
    }
}
