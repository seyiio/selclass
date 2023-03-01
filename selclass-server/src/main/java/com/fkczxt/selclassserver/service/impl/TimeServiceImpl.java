package com.fkczxt.selclassserver.service.impl;

import com.fkczxt.selclassserver.pojo.Time;
import com.fkczxt.selclassserver.mapper.TimeMapper;
import com.fkczxt.selclassserver.service.ITimeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
