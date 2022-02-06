package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    //多条件分页查询
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){
        PageInfo allUserByPage = userService.findAllUserByPage(userVo);
        return new ResponseResult(true, 200, "成功", allUserByPage);
    }

    //用户登录
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {

        User login = userService.login(user);
        if(login != null){
            //保存id和access_token到session
            HttpSession session = request.getSession();
            String s = UUID.randomUUID().toString();
            session.setAttribute("access_token",s);
            session.setAttribute("user_id",login.getId());

            Map<String, Object> map = new HashMap<>();
            map.put("access_token",s);
            map.put("user_id",login.getId());
            return new ResponseResult(true, 200, "登陆成功", map);
        }else {
            return new ResponseResult(true, 400, "用户登录失败", null);
        }
    }


    //分配角色回显
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelationRoleById(int id){
        List<Role> list = userService.findUserRelationRoleById(id);
        return new ResponseResult(true, 200, "分配角色回显成功", list);
    }

    //分配角色
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){
        userService.userContextRole(userVo);
        return new ResponseResult(true, 200, "成功", null);
    }

    //获取用户权限，进行菜单动态展示
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        //获取请求头的token
        String header_token = request.getHeader("Authorization");
        //获取session里的token
        String session_token =(String) request.getSession().getAttribute("access_token");
        //判断是否一致
        if(header_token.equals(session_token)){
            //获取用户Id
            Integer user_id =(Integer) request.getSession().getAttribute("user_id");
            ResponseResult userPermissions = userService.getUserPermissions(user_id);
            return userPermissions;
        } else{
            return new ResponseResult(false, 400, "获取失败", null);
        }
    }

}
