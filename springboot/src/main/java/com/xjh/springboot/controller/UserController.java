package com.xjh.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.xjh.springboot.dao.UserDao;
import com.xjh.springboot.pojo.QueryInfo;
import com.xjh.springboot.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
/*@Author 四原色
 * 2021-07-30 19:55:00
 */
@RestController
public class UserController {
    @Resource(name = "userDao")
    UserDao userDao;

    @RequestMapping("/allUser")
    public String getUserList(QueryInfo queryInfo){
//        String name,int pageStart,int pageSize
//        获取最大列表数,和当前编号

//        获取最大用户数,获取最大查询数量
        int userCount = userDao.getAllUserNums("%"+queryInfo.getQuery()+"%");

//获取当前开始查询的编号
        int pageStart = (queryInfo.getPageNum()-1) * queryInfo.getPageSize();

        List<User> userList = userDao.getAllUser("%"+queryInfo.getQuery()+"%",pageStart, queryInfo.getPageSize());

        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("unambers",userCount);
        res.put("data",userList);

        String val = JSON.toJSONString(res);
        return val;
    }
}
