package com.fkczxt.selclassserver.controller;

import com.fkczxt.selclassserver.pojo.Class;
import com.fkczxt.selclassserver.pojo.Cs;
import com.fkczxt.selclassserver.pojo.RespBean;
import com.fkczxt.selclassserver.pojo.User;
import com.fkczxt.selclassserver.service.IClassService;
import com.fkczxt.selclassserver.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/getaclass")
    @ApiOperation(value = "获取课程")
    public Class getclass(@RequestBody String id){
        id=id.split("\"")[3];
        return iClassService.getclass(Integer.parseInt(id));
    }
    @PostMapping("/changeclass")
    @ApiOperation(value = "修改课程")
    public RespBean changeclass(@RequestBody Class c){
        return iClassService.changeclass(c);
    }
    @PostMapping("/deleteclass")
    @ApiOperation(value = "删除课程")
    public RespBean delclass(@RequestBody Class c){
        return iClassService.delclass(c);
    }

    @GetMapping("/students")
    @ApiOperation(value = "获取选课学生名单")
    public List<User> getstudents(int id){
        return iClassService.getstudents(id);
    }
    @PostMapping("/modifystudentpoint")
    @ApiOperation(value = "修改学生成绩")
    public RespBean modifypoint(int uid,int cid,Double point){
        return iClassService.modify(uid,cid,point);
    }
    @PostMapping("/deletestu")
    @ApiOperation(value = "删除学生")
    public RespBean deletestu(@RequestBody Cs cs){
        return iClassService.deletestu(cs);
    }
}
