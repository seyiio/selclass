package com.fkczxt.selclassserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fkczxt.selclassserver.mapper.CsMapper;
import com.fkczxt.selclassserver.mapper.UserMapper;
import com.fkczxt.selclassserver.pojo.Class;
import com.fkczxt.selclassserver.mapper.ClassMapper;
import com.fkczxt.selclassserver.pojo.Cs;
import com.fkczxt.selclassserver.pojo.RespBean;

import com.fkczxt.selclassserver.pojo.User;
import com.fkczxt.selclassserver.service.IClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CsMapper csMapper;


    @Override
    public List<Class> getClasses() {
        return classMapper.selectList(null);
    }

    @Override
    public List<User> getstudents(int id) {
        List<User> users=new ArrayList<>();
        List<Cs> cs =csMapper.selectList(new QueryWrapper<Cs>().eq("classid",id));
        cs.forEach(cs1 -> {

            users.add(userMapper.selectById(cs1.getStudentid()));
        });
        return users;
    }

    @Override
    public RespBean modify(int uid, int cid, Double point) {
        if (csMapper.update(null ,new UpdateWrapper<Cs>().eq("studentid",uid).eq("classid",cid).set("fraction",point))>0)
            return RespBean.success("修改成功");
        return RespBean.error("修改失败");
    }

    @Override
    public List<Class> getSelClass(Long id) {
        String  a= userMapper.selectById(id).getType();
        if (a.equals("0")){return null;}else if(a.equals("3")){
       List<Cs> csList= csMapper.selectList(new QueryWrapper<Cs>().eq("studentid",id));
        List<Class> classes = new ArrayList<>();
        csList.forEach(cs->{
           classes.add( classMapper.selectById(cs.getClassid()));
        });
        return classes;}
        else{
            return classMapper.selectList( new QueryWrapper<Class>().eq("teacher",id));
        }
    }


}



