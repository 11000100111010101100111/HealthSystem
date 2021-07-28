package com.xjh.springboot.controller.testController;

import com.alibaba.fastjson.JSON;
import com.xjh.springboot.dao.UserDao;
import com.xjh.springboot.pojo.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
public class LoginController {
    //依赖注入
    @Resource(name = "userDao")
    UserDao userDao;

    @CrossOrigin //方法级跨域
    @RequestMapping("/login")
    public String login(@RequestBody User user){
        String flage = "error";

        System.out.println("==>用户登录:"+user.toString());
        User item = userDao.getUserByMessage(user.getUsername(),user.getPassword());
        System.out.println(item.toString());
        if(item != null){
            flage = "ok";
        }
        HashMap<String , Object> stringObjectHashMap = new HashMap<String ,Object>();
        stringObjectHashMap.put("flage",flage);
        stringObjectHashMap.put("user",item);
        String login_user_json = JSON.toJSONString(stringObjectHashMap);

        return login_user_json;
    }
}