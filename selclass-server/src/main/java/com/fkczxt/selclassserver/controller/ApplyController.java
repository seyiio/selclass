package com.fkczxt.selclassserver.controller;

import com.fkczxt.selclassserver.pojo.Apply;
import com.fkczxt.selclassserver.pojo.RespBean;
import com.fkczxt.selclassserver.pojo.User;
import com.fkczxt.selclassserver.service.IApplyService;
import com.fkczxt.selclassserver.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fk
 * @since 2023-01-18
 */
@RestController
@RequestMapping("/api/apply")
public class ApplyController {
    @Autowired
    private IApplyService iApplyService;
    @Autowired
    private IUserService userService;
    @PostMapping("/delclass")
    @ApiOperation("删除课程")
    public RespBean delclass(@RequestBody Apply apply){
        return iApplyService.delclass(apply);
    }
    @GetMapping("/getApplyList")
    @ApiOperation("获取申请列表")
    public RespBean getapplylist(Principal principal){
        if(null==principal)
            return  RespBean.error("请先登录");
        User user=userService.getUserByusername(principal.getName());
        if(user.getType().equals("3"))
            return RespBean.error("权限不足");

        return iApplyService.getapplylist(user.getId(),user.getType());
    }
    @PostMapping("/permit")
    @ApiOperation("批准申请")
    public RespBean permit(@RequestBody Apply apply,Principal principal){
        if(null==principal)
            return  RespBean.error("请先登录");
        User user=userService.getUserByusername(principal.getName());
        if(!user.getType().equals("0"))
            return RespBean.error("权限不足");
        return iApplyService.permit(apply,user.getName());
    }
    @PostMapping("/refuse")
    @ApiOperation("拒绝申请")
    public RespBean refuse(@RequestBody Apply apply,Principal principal){
        if(null==principal)
            return  RespBean.error("请先登录");
        User user=userService.getUserByusername(principal.getName());
        if(!user.getType().equals("0"))
            return RespBean.error("权限不足");
        return iApplyService.refuse(apply,user.getName());
    }
    @PostMapping(/*"/addapply"*/"/addApply")
    @ApiOperation("申请课程")
    public RespBean apply(@RequestBody Apply apply,Principal principal){
        if(null==principal)
            return  RespBean.error("请先登录");
        User user=userService.getUserByusername(principal.getName());
        if(!user.getType().equals("1"))
            return RespBean.error("权限不足");
        return iApplyService.addapply(apply);
    }
}
