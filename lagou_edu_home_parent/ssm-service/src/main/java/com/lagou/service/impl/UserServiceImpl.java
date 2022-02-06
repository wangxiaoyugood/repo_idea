package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {
        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());
        List<User> allUserByPage = userMapper.findAllUserByPage(userVo);
        PageInfo<User> pageInfo = new PageInfo<>(allUserByPage);
        return pageInfo;
    }

    @Override
    public User login(User user) throws Exception {
        User login = userMapper.login(user);
        if(login != null && Md5.verify(user.getPassword(),"lagou",login.getPassword())){
            return login;
        } else {
            return null;
        }
    }

    @Override
    public List<Role> findUserRelationRoleById(int id) {

        List<Role> list = userMapper.findUserRelationRoleById(id);
        return list;
    }

    @Override
    public void userContextRole(UserVo userVo) {

        //清空关系
        userMapper.deleteUserContextRole(userVo.getUserId());
        //再建
        Date date = new Date();
        for(int i : userVo.getRoleIdList()){
            User_Role_relation urr = new User_Role_relation();
            urr.setUserId(userVo.getUserId());
            urr.setRoleId(i);
            urr.setCreatedTime(date);
            urr.setUpdatedTime(date);
            urr.setCreatedBy("system");
            urr.setUpdatedby("system");
            userMapper.userContextRole(urr);
        }

    }

    //获取用户权限信息
    @Override
    public ResponseResult getUserPermissions(int userid) {
        //获取当前用户拥有的角色
        List<Role> roles = userMapper.findUserRelationRoleById(userid);
        //获取角色ID
        ArrayList<Integer> roleIds = new ArrayList<>();
        for(Role role : roles){
            roleIds.add(role.getId());
        }
        //根据角色Id查询父菜单
        List<Menu> parentMenus = userMapper.findParentMenuByRoleId(roleIds);
        //查询子菜单
        for(Menu menu:parentMenus){
            List<Menu> subMenus = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenus);
        }
        //获取资源信息
        List<Resource> resources = userMapper.findResourceByRoleId(roleIds);
        //封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("menuList",parentMenus);
        map.put("resourceList",resources);
        return new ResponseResult(true, 200, "成功", map);
    }
}
