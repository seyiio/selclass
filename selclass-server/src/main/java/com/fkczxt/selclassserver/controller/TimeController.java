package com.fkczxt.selclassserver.controller;

import com.fkczxt.selclassserver.pojo.RespBean;
import com.fkczxt.selclassserver.pojo.Time;
import com.fkczxt.selclassserver.pojo.User;
import com.fkczxt.selclassserver.service.ITimeService;
import com.fkczxt.selclassserver.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fk
 * @since 2023-03-01
 */
@RestController
@RequestMapping("/api/time")
public class TimeController {
    @Autowired
    private ITimeService iTimeService;
    @ApiOperation(value = "获取选课时间")

    @GetMapping("/selclasstime")
    public Time gettime(int id){
        return iTimeService.gettime(id);
    }
    @ApiOperation(value = "更改选课时间")

    @PostMapping("/changeTime")
    public RespBean changeTime(@RequestBody String time){
        String [] time1=time.split("\"");

        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
        int id=Integer.parseInt(time1[8].charAt(1)+"");
        try {
            LocalDateTime time2= LocalDateTime.ofInstant(format.parse(time1[3]).toInstant(), ZoneId.systemDefault()).plusHours(8);
            LocalDateTime time3= LocalDateTime.ofInstant(format.parse(time1[5]).toInstant(), ZoneId.systemDefault()).plusHours(8);
            return iTimeService.changeTime(new Time(time2,time3,id));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
