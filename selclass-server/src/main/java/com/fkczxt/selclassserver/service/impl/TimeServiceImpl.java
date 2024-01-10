package com.fkczxt.selclassserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fkczxt.selclassserver.pojo.RespBean;
import com.fkczxt.selclassserver.pojo.Time;
import com.fkczxt.selclassserver.mapper.TimeMapper;
import com.fkczxt.selclassserver.service.ITimeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fk
 * @since 2023-03-01
 */
@Service
public class TimeServiceImpl extends ServiceImpl<TimeMapper, Time> implements ITimeService {
    @Autowired
    private TimeMapper timeMapper ;
    public Time gettime(int id){
        return timeMapper.selectOne(new QueryWrapper<Time>().eq("id",id));
}

    @Override
    public RespBean changeTime(Time time) {
        timeMapper.update(time, new UpdateWrapper<Time>().eq("id",time.getId()));
        return RespBean.success("修改成功");
    }
}
