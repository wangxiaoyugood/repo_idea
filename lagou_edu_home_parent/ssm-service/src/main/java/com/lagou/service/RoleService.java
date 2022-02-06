package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;

import java.util.List;

public interface RoleService {

    //根据条件查询所有角色
    public List<Role> findAllRole(Role role);

    //根据角色ID查询关联菜单
    public List<Integer> findMenuByRoleId(int roleId);

    //为角色分配菜单
    public void roleContextMenu(RoleMenuVo roleMenuVo);

    //删除角色
    public void deleteRole(int roleId);
}
