package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;

import java.util.List;

public interface UserService {
    //分页多条件
    public PageInfo findAllUserByPage(UserVo userVo);


    //根据用户名查询具体信息
    public User login(User user) throws Exception;

    //根据Id回显角色
    public List<Role> findUserRelationRoleById(int id);

    //用户关联角色
    public void userContextRole(UserVo userVo);

    //获取用户权限，进行菜单动态展示
    public ResponseResult getUserPermissions(int userid);

}
