package com.xjh.springboot.dao;

import com.xjh.springboot.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
/*@Author 四原色
 * 2021-07-30 19:55:00
 */
@Repository
public interface UserDao {

//    查询语句
//    SELECT count(id) FROM `easyuser` where `username` = "四原色" and `password` = "123456" AND `state` = 1
//    获取当前登录用户账号密码
    public User getUserByMessage(@Param("username") String username, @Param("password") String password);

    //分页查询获取全部用户信息
    public List<User> getAllUser(@Param("username")String username,@Param("pageStart") int pageStart,@Param("pageSize") int pageSize);
    //分页查询获取全部用户数目
    public Integer getAllUserNums(@Param("username")String username);

    //修改用户状态
    public int modifyUserState(@Param("id") Integer id,@Param("state") Boolean state);

    //添加用户
    public int addUser(User user);

    //删除用户,根据用户id删除指定用户
    public int removeUser(int id);

    //修改用户信息
    //根据id获取用户信息
    public User getUserById(int id);
    //更新用户信息
    public int updateUser(User user);
}
