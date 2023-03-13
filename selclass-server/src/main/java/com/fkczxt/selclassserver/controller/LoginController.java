package com.fkczxt.selclassserver.controller;

import com.fkczxt.selclassserver.pojo.RespBean;
import com.fkczxt.selclassserver.pojo.User;
import com.fkczxt.selclassserver.pojo.UserLoginParam;
import com.fkczxt.selclassserver.service.IUserService;
import com.sun.net.httpserver.HttpsServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Api( "LoginController")
@RestController

public class LoginController {
    @Autowired
    private IUserService userService;
    @ApiOperation(value = "登录后返回token")

    @PostMapping("/api/login")
    public RespBean login(@RequestBody UserLoginParam userLoginParam, HttpServletRequest request){
        return  userService.login(userLoginParam.getUsername(),userLoginParam.getPassword(),request);
    }
    @ApiOperation(value = "获取当前登录信息")

    @GetMapping("/api/admin/info")
    public User getUserInfo(Principal principal){
        if(null==principal)
            return  null;
        String username=principal.getName();
        User user=userService.getUserByusername(username);
        user.setPassword(null);
        return user;
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/api/logout")
    public RespBean logout(){
        return RespBean.success("注销成功");
    }
}
