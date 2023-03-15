package com.fkczxt.selclassserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fkczxt.selclassserver.mapper.ClassMapper;
import com.fkczxt.selclassserver.pojo.Class;
import com.fkczxt.selclassserver.pojo.Cs;
import com.fkczxt.selclassserver.mapper.CsMapper;
import com.fkczxt.selclassserver.pojo.RespBean;
import com.fkczxt.selclassserver.service.ICsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
    public RespBean selclass(Cs cs){
        if(csMapper.selectOne(new QueryWrapper<Cs>().eq("classid",cs.getClassid()))!=null) return RespBean.error("你已经选过这门课程了");
        List<String> time= Arrays.asList(classMapper.selectById(cs.getClassid()).getTime().split(" "));
        List<Cs> csList= csMapper.selectList(null);
        AtomicBoolean flag = new AtomicBoolean(false);
        csList.forEach(acs->{
               Class class1= classMapper.selectOne(new QueryWrapper<Class>().eq("classid",acs.getClassid()));
               List<String > time2= Arrays.asList(classMapper.selectById(class1).getTime().split(" "));
               if(!Collections.disjoint(time,time2)){
                   flag.set(true);
            }
        });
        if (flag.get()){
            return RespBean.error("该课程与已选课程时间冲突");
        }
        csMapper.insert(cs);
        Class c=classMapper.selectOne(new QueryWrapper<Class>().eq("classid",cs.getClassid()));
        c.setSelected(c.getSelected()+1);
        classMapper.updateById(c);
        return RespBean.success("选课成功");

    }

    @Override
    public RespBean delclass(Cs cs) {
if (csMapper.selectOne(new QueryWrapper<Cs>().eq("classid",cs.getClassid()))==null) {
    return RespBean.error("发生了意外");
}
        Class c=classMapper.selectOne(new QueryWrapper<Class>().eq("classid",cs.getClassid()));
        c.setSelected(c.getSelected()-1);
        classMapper.updateById(c);
csMapper.delete(new QueryWrapper<Cs>().eq("classid",cs.getClassid()));
return RespBean.success("退课成功");
}
    }

