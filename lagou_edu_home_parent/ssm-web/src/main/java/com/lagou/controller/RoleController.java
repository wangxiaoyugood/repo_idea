package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> allRole = roleService.findAllRole(role);
        ResponseResult result = new ResponseResult(true, 200, "成功", allRole);
        return result;
    }

    @Autowired
    private MenuService menuService;
    //查询所有父子菜单信息
    @RequestMapping("/findAllMenu")
    public ResponseResult findSubMenuListByPid(){
        List<Menu> subMenu = menuService.findSubMenuListByPid(-1);
        Map<String,Object> map = new HashMap<>();
        map.put("parentMenuList", subMenu);
        ResponseResult result = new ResponseResult(true, 200, "查询所有父子菜单信息成功", map);
        return result;
    }

    //根据角色ID查询关联菜单
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(int roleId){
        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);
        ResponseResult result = new ResponseResult(true, 200, "成功", menuByRoleId);
        return result;
    }

    //为角色分配菜单
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.roleContextMenu(roleMenuVo);
        ResponseResult result = new ResponseResult(true, 200, "成功", null);
        return result;
    }

    //删除角色
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(int id){
        roleService.deleteRole(id);
        ResponseResult result = new ResponseResult(true, 200, "成功", null);
        return result;
    }

}
