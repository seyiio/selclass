package com.fkczxt.selclassserver.service.impl;

import com.fkczxt.selclassserver.pojo.Class;
import com.fkczxt.selclassserver.mapper.ClassMapper;
import com.fkczxt.selclassserver.service.IClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fk
 * @since 2023-01-18
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements IClassService {
    @Autowired
    private ClassMapper classMapper;

    @Override
    public List<Class> getSelClass() {
        return classMapper.selectList(null);
    }
}
