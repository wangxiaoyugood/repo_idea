package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.Role_menu_relation;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    @Override
    public List<Integer> findMenuByRoleId(int roleId) {
        List<Integer> menuId = roleMapper.findMenuByRoleId(roleId);
        return menuId;
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {
        //清空中间表
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());
        //分配添加关联
        for(int mid: roleMenuVo.getMenuIdList()){
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");
            roleMapper.roleContextMenu(role_menu_relation);
        }
    }

    @Override
    public void deleteRole(int roleId) {
        //删除角色
        roleMapper.deleteRole(roleId);
        //清空中间表
        roleMapper.deleteRoleContextMenu(roleId);
    }
}
