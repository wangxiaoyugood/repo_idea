package com.lagou.service.impl;

import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;


    @Override
    public List<Menu> findSubMenuListByPid(int pid) {
        List<Menu> subMenu = menuMapper.findSubMenuListByPid(pid);
        return subMenu;
    }

    @Override
    public List<Menu> findAllMenu() {
        List<Menu> allMenu = menuMapper.findAllMenu();
        return allMenu;
    }

    @Override
    public Menu findMenuById(int id) {
        menuMapper.findMenuById(id);
        return null;
    }
}
