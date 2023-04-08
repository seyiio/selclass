package com.fkczxt.selclassserver.controller;

import com.fkczxt.selclassserver.pojo.Class;
import com.fkczxt.selclassserver.pojo.RespBean;
import com.fkczxt.selclassserver.pojo.User;
import com.fkczxt.selclassserver.service.IClassService;
import com.fkczxt.selclassserver.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fk
 * @since 2023-01-18
 */
@RestController
@RequestMapping("/api/class")
public class ClassController {
    @Autowired
    private IClassService iClassService;
    @Autowired
    private IUserService userService;
    @GetMapping("/getclass")
    @ApiOperation(value = "获取选课列表")
    public List<Class> getClasses(){
    return iClassService.getClasses();
}
    @GetMapping("/getselclass")
    @ApiOperation(value = "获取课表")
    public List<Class> getSelClass(Principal principal){
        if(null==principal)
            return  null;
        String username=principal.getName();
        return iClassService.getSelClass(userService.getUserByusername(username).getId());
    }
    @GetMapping("/getselclassbyt")
    @ApiOperation(value = "获取课表")
    public List<Class> getSelClassbyt(Principal principal){
        if(null==principal)
            return  null;
        String username=principal.getName();
        return iClassService.getSelClass(userService.getUserByusername(username).getId());
    }

}
