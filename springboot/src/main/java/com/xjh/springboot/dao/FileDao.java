package com.xjh.springboot.dao;

import com.xjh.springboot.pojo.File;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDao {
    //上传文件
    public int uploadFile(File file);
    //判断这个文件名称是否存在
    public int hasFileByName(String name);
    //文件被下载一次
    public int download(Long id);
    //文件被浏览一次
    public int browseFile(Long id);

    //查找文件
    public List<String> findFile(String key);
}
