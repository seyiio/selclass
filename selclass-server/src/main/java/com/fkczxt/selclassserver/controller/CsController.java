package com.fkczxt.selclassserver.controller;
import com.fkczxt.selclassserver.pojo.Class;
import com.fkczxt.selclassserver.pojo.Cs;
import com.fkczxt.selclassserver.pojo.RespBean;
import com.fkczxt.selclassserver.service.IClassService;
import com.fkczxt.selclassserver.service.ICsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
@RequestMapping("/api/cs")
public class CsController implements InitializingBean {
    @Autowired
    private ICsService iCsService;
    @Autowired
    private IClassService iClassService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisScript<Long> redisScript;
    private Map<Integer, Boolean> EmptyStockMap = new HashMap<>();
    @PostMapping ("/selectclass")
    @ApiOperation(value = "选课")
    public RespBean selectClass(@RequestBody Cs cs){
        ValueOperations valueOperations=redisTemplate.opsForValue();
        Long stock=(Long) redisTemplate.execute(redisScript,Collections.singletonList("classid"+cs.getClassid()), Collections.EMPTY_LIST);
        if (stock < 0) {
            EmptyStockMap.put(cs.getClassid(), true);
            valueOperations.increment("classid:" + cs.getClassid());
            return RespBean.error("EMPTY_STOCK");
        }
        return iCsService.selclass(cs);
    }
    @PostMapping ("/deleteclass")
    @ApiOperation(value = "选课")
    public RespBean deleteClass(@RequestBody Cs cs){

        return iCsService.delclass(cs);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        List<Class> list =iClassService.getClasses();
        if(CollectionUtils.isEmpty(list)){
            return;
        }
        list.forEach(classVo->{
           redisTemplate.opsForValue().set("classid"+classVo.getClassid(),classVo.getNumber()-classVo.getSelected());
        });
    }
}
