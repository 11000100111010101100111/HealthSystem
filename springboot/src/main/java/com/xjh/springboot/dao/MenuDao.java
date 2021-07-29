package com.xjh.springboot.dao;

import com.xjh.springboot.pojo.MainMenu;
import com.xjh.springboot.pojo.SubMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao {

//    SELECT mm.*,sm.id as sid ,sm.title as stitle,sm.path as spath FROM `mainmenu` mm,submenu sm WHERE mm.id = sm.mid
    public List<MainMenu> getMenus();

}
