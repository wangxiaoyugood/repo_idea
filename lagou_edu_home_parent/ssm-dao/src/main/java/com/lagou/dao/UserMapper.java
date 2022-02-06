package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {

    //多条件分页查询
    public List<User> findAllUserByPage(UserVo userVo);

    //根据用户名查询具体信息
    public User login(User user);

    //根据用户Id清空中间表
    public void deleteUserContextRole(int userId);

    //分配角色
    public void userContextRole(User_Role_relation user_role_relation);

    //分配角色回显
    public List<Role> findUserRelationRoleById(int id);

    //根据角色查询父菜单
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    //根据父菜单查询子菜单
    public List<Menu> findSubMenuByPid(int pid);

    //获取用户的资源
    public List<Resource> findResourceByRoleId(List<Integer> ids);

}
