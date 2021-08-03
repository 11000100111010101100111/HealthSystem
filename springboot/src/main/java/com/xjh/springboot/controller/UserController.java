package com.xjh.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.xjh.springboot.dao.UserDao;
import com.xjh.springboot.pojo.QueryInfo;
import com.xjh.springboot.pojo.User;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin
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
        res.put("userCount",userCount);
        res.put("data",userList);
//        System.out.println("用户数："+userCount);
        String val = JSON.toJSONString(res);

        return val;
    }
    @CrossOrigin
    @RequestMapping("/changeState")
    public String modifyUserState(@RequestParam("id")Integer id,@RequestParam("state") Boolean state){

        int res = userDao.modifyUserState(id,state);
        return res>0?"succeed":"error";
    }

    @CrossOrigin
    @RequestMapping("/addUser")
    public String addUser(@RequestBody User user){
        System.out.println("请求添加用户："+user.toString());

        String str="error";

        if(userDao.hasUser(user.getUsername()) == 0) {
            user.setRole("普通用户");
            user.setState(false);
            int i = userDao.addUser(user);
            str = i > 0 ? "succeed" : "error";

            System.out.println("添加" + (i > 0 ? "成功！" : "失败！"));

            return str;
        }else{
            str = "用户"+user.getUsername()+"已存在！";
        }
        return str;
    }

    @CrossOrigin
    @RequestMapping("/removeUser")
    public String removeUser(int id){
//        int res = userDao.removeUser(id);
//        String str = res>0?"succeed":"error";
//        return str;
        return userDao.removeUser(id)>0?"succeed":"error";
    }

    @CrossOrigin
    @RequestMapping("/selectUser")
    public String selectUser(int id){
        HashMap<String,Object> list = new HashMap<String,Object>();
        try{
            User user = userDao.getUserById(id);
            list.put("user",user);
            list.put("flage","succeed");
        }
        catch (Exception e){
            list.put("flage","succeed");
        }
        return JSON.toJSONString(list);
    }
    @CrossOrigin
    @RequestMapping("/modifyUser")
    public String modifyUser(@RequestBody User user){
        int flage =0;
        try {
            flage = userDao.updateUser(user);
        }catch (Exception e){
            flage = -1;
        }
        return flage>0?"succeed":"error";
    }

    @CrossOrigin
    @RequestMapping("/getUid")
    public String getUid(String username){
        HashMap<String ,Object> val = new HashMap<String,Object>();
        try {
            int id = userDao.findUidByName(username);
            val.put("id",id);
            val.put("flag","succed");
        }catch (Exception e){
            System.out.println("======"+username);
            val.put("flag","error");
        }
        return JSON.toJSONString(val);
    }
}
