package com.fkczxt.selclassserver.controller;

import com.fkczxt.selclassserver.pojo.RespBean;
import com.fkczxt.selclassserver.pojo.User;
import com.fkczxt.selclassserver.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fk
 * @since 2023-01-18
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private IUserService iUserService;
    @GetMapping("/getusers")
    @ApiOperation("获取用户")
    public RespBean getusers(){
        return iUserService.getusers();
    }
    @PostMapping("/deleteuser")
    @ApiOperation("删除用户")
    public RespBean deleteuser(@RequestBody User user){
        return iUserService.deleteuser(user);
    }
    @PostMapping("/changeuser")
    @ApiOperation("更新用户")
    public RespBean changeuser(@RequestBody User user){
        return iUserService.changeuser(user);
    }
    @PostMapping("/getuser")
    @ApiOperation("获取用户")
    public User getuser(@RequestBody String username){
        username=username.split("\"")[3];
        return iUserService.getUserByusername(username);
    }
}
