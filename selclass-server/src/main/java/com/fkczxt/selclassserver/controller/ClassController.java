package com.fkczxt.selclassserver.controller;

import com.fkczxt.selclassserver.pojo.Class;
import com.fkczxt.selclassserver.service.IClassService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private IClassService iClassService;
    @GetMapping("/getclass")
@ApiOperation(value = "获取选课列表")
    public List<Class> getSelClass(){
    return iClassService.getSelClass();
}
}
