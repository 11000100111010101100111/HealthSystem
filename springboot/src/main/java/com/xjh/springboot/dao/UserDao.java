package com.xjh.springboot.dao;

import com.xjh.springboot.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface UserDao {

//    查询语句
//    SELECT count(id) FROM `easyuser` where `username` = "四原色" and `password` = "123456" AND `state` = 1
    public User getUserByMessage(@Param("username") String username, @Param("password") String password);


}
