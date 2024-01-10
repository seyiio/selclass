package com.fkczxt.selclassserver.controller;
import com.fkczxt.selclassserver.config.JsonUtil;
import com.fkczxt.selclassserver.pojo.Class;
import com.fkczxt.selclassserver.pojo.Cs;
import com.fkczxt.selclassserver.pojo.RespBean;
import com.fkczxt.selclassserver.pojo.SelclassMessage;
import com.fkczxt.selclassserver.rabbitmq.MQReceiver;
import com.fkczxt.selclassserver.rabbitmq.MQSender;
import com.fkczxt.selclassserver.service.IClassService;
import com.fkczxt.selclassserver.service.ICsService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fk
 * @since 2023-01-18
 */
@RestController
@Slf4j
@RequestMapping("/api/cs")
public class CsController implements InitializingBean {
    @Autowired
    private ICsService iCsService;
    @Autowired
    private IClassService iClassService;
    @Autowired
    private MQSender mqSender;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisScript<Long> redisScript;
    private final Map<Integer, Boolean> EmptyStockMap = new HashMap<>();
    @PostMapping ("/selectclass")
    @ApiOperation(value = "选课")
    public RespBean selectClass(@RequestBody Cs cs){
        ValueOperations valueOperations=redisTemplate.opsForValue();
        //内存标记，减少Redis的访问
        if (EmptyStockMap.get(cs.getClassid())) {
            return RespBean.error("EMPTY_STOCK");
        }
        Long stock=(Long) redisTemplate.execute(redisScript,Collections.singletonList("classid"+cs.getClassid()), Collections.EMPTY_LIST);
        if (stock < 0) {
            EmptyStockMap.put(cs.getClassid(), true);
            valueOperations.increment("classid:" + cs.getClassid());
            return RespBean.error("EMPTY_STOCK");
        }
        RespBean respBean=iCsService.beforesel(cs);
        if (respBean.getCode()==500) return respBean;
        SelclassMessage selmessage=new SelclassMessage(cs);
        mqSender.sendselclassMessage(JsonUtil.object2JsonStr(selmessage));
        return RespBean.success("选课成功");
    }
    @PostMapping ("/deleteclass")
    @ApiOperation(value = "退课")
    public RespBean deleteClass(@RequestBody Cs cs){

        return iCsService.delclass(cs);
    }
    @GetMapping("/getfraction")
    @ApiOperation(value = "获取分数")
    public String  getfraction(Integer id, Long sid ){

        return iCsService.getfraction(id,sid);
    }



    @Override
    public void afterPropertiesSet() throws Exception {
        List<Class> list =iClassService.getClasses();
        if(CollectionUtils.isEmpty(list)){
            return;
        }
        list.forEach(classVo->{
           redisTemplate.opsForValue().set("classid"+classVo.getClassid(),classVo.getNumber()-classVo.getSelected());
            EmptyStockMap.put(classVo.getClassid(), false);
        });
    }
}
