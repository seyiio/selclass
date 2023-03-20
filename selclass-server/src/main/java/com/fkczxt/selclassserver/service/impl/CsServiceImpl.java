package com.fkczxt.selclassserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fkczxt.selclassserver.controller.CsController;
import com.fkczxt.selclassserver.mapper.ClassMapper;
import com.fkczxt.selclassserver.pojo.Class;
import com.fkczxt.selclassserver.pojo.Cs;
import com.fkczxt.selclassserver.mapper.CsMapper;
import com.fkczxt.selclassserver.pojo.RespBean;
import com.fkczxt.selclassserver.service.IClassService;
import com.fkczxt.selclassserver.service.ICsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fk
 * @since 2023-01-18
 */
@Service
public class CsServiceImpl extends ServiceImpl<CsMapper, Cs> implements ICsService {
    @Autowired
    private CsMapper csMapper;
    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private ClassServiceImpl classService;
    @Autowired
    private RedisTemplate redisTemplate;


    public RespBean selclass(Cs cs) {

        csMapper.insert(cs);
        boolean a = classService.update(new UpdateWrapper<Class>().setSql("selected =" + "selected+1").eq("classid", cs.getClassid()).lt("selected", 120));
        return RespBean.success("选课成功");
    }

    @Override
    public RespBean delclass(Cs cs) {
        if (csMapper.selectOne(new QueryWrapper<Cs>().eq("classid", cs.getClassid())) == null) {
            return RespBean.error("发生了意外");
        }
        Class c = classMapper.selectOne(new QueryWrapper<Class>().eq("classid", cs.getClassid()));

        classMapper.updateById(c);
        csMapper.delete(new QueryWrapper<Cs>().eq("classid", cs.getClassid()));
        backnum(c);
        return RespBean.success("退课成功");
    }

    public void backnum(Class classVo) {
        redisTemplate.opsForValue().increment("classid" + classVo.getClassid());
    }
    @Override
    public RespBean beforesel(Cs cs) {
        Class c1 = classMapper.selectOne(new QueryWrapper<Class>().eq("classid", cs.getClassid()));
        if (csMapper.selectOne(new QueryWrapper<Cs>().eq("classid", cs.getClassid()).eq("studentid", cs.getStudentid())) != null) {
            backnum(c1);
            return RespBean.error("你已经选过这门课程了");
        }
        List<String> time = Arrays.asList(classMapper.selectById(cs.getClassid()).getTime().split(" "));
        List<Cs> csList = csMapper.selectList(new QueryWrapper<Cs>().eq("studentid", cs.getStudentid()));
        AtomicBoolean flag = new AtomicBoolean(false);
        csList.forEach(acs -> {
            Class class1 = classMapper.selectOne(new QueryWrapper<Class>().eq("classid", acs.getClassid()));
            List<String> time2 = Arrays.asList(classMapper.selectById(class1).getTime().split(" "));
            if (!Collections.disjoint(time, time2)) {
                flag.set(true);
            }
        });
        if (flag.get()) {
            backnum(c1);
            return RespBean.error("该课程与已选课程时间冲突");
        }
        return RespBean.success("");
    }
}


