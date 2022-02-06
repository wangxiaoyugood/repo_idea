package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> allMenu = menuService.findAllMenu();
        ResponseResult result = new ResponseResult(true, 200, "查询成功", allMenu);
        return result;
    }

    //回显菜单信息
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(int id){
        //如果id为-1则添加
        if(id==-1){
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList", subMenuListByPid);
            return new ResponseResult(true, 200, "成功", map);
        } else{
            //id不等于-1则修改
            Menu menu = menuService.findMenuById(id);
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList", subMenuListByPid);
            return new ResponseResult(true, 200, "成功", map);
        }

    }


}
