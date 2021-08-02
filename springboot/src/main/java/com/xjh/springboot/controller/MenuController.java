package com.xjh.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.xjh.springboot.dao.MenuDao;
import com.xjh.springboot.pojo.MainMenu;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
/*@Author 四原色
 * 2021-07-29 15:45:00
 */
@RestController
public class MenuController {
    @Resource(name = "menuDao")
    MenuDao menuDao;

    @RequestMapping("/menus")
    public String getAllMenus(){
        System.out.println("开始查询菜单列表:");
        HashMap<String,Object> menus = new HashMap<String ,Object>();

        int status = 404;//操作失败：404，操作成功：200

        List<MainMenu> menuList = menuDao.getMenus();
        if (menuList != null){
            status = 200;
            menus.put("menus",menuList);
            for (MainMenu mainMenu : menuList) {
                System.out.println(mainMenu.toString());
            }
            System.out.println("查询菜单列表成功！");
        }
        menus.put("status",status);
        String menu = JSON.toJSONString(menus);
        return menu;
    }
}
