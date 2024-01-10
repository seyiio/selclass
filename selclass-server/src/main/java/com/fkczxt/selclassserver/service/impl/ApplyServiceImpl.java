package com.fkczxt.selclassserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.fkczxt.selclassserver.mapper.ApplyMapper;
import com.fkczxt.selclassserver.mapper.ClassMapper;
import com.fkczxt.selclassserver.pojo.Apply;
import com.fkczxt.selclassserver.pojo.Class;
import com.fkczxt.selclassserver.pojo.RespBean;
import com.fkczxt.selclassserver.service.IApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply> implements IApplyService {
    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private ApplyMapper applyMapper;
    @Override
    public RespBean delclass(Apply apply) {
        int id=apply.getClassid();
        if (classMapper.selectById(id)!=null){
            Class aClass = classMapper.selectById(id);
            apply.setType(1);
            apply.setClassname(aClass.getClassname());
            apply.setTeachername(aClass.getTeachername());
            apply.setTeacher(aClass.getTeacher());
            apply.setPoint(aClass.getPoint());
            apply.setNumber(aClass.getNumber());
            apply.setTime(aClass.getTime());
            apply.setStarttime(LocalDateTime.now());
            apply.setEndtime(LocalDateTime.now().plusDays(7));
            apply.setApplytime(LocalDateTime.now());
            apply.setStatus(0);
            applyMapper.insert(apply);
            return RespBean.success("申请成功");
        }else {
            return RespBean.error("申请失败");
        }
        }

    @Override
    public RespBean getapplylist(Long id,String type) {
        if (type.equals("1")) {
            return RespBean.success("查询成功", applyMapper.selectList(new QueryWrapper<Apply>().eq("teacher", id)));
        }else {
            return RespBean.success("查询成功", applyMapper.selectList(null));
        }

    }

    @Override
    public RespBean permit(Apply apply,String name) {
        Apply apply1 = applyMapper.selectById(apply.getId());
        if (apply1==null){
            return RespBean.error("申请不存在");
        }
        apply1.setStatus(1);
        apply1.setDealperson(name);
        apply1.setDealtime(LocalDateTime.now());
        apply1.setDealreason(apply.getDealreason());
        applyMapper.updateById(apply1);
        if (apply1.getType()==1){
            classMapper.deleteById(apply1.getClassid());
        }
        if (apply1.getType()==2){
            Class aClass = new Class();
            aClass.setClassname(apply1.getClassname());
            aClass.setTeachername(apply1.getTeachername());
            aClass.setTeacher(apply1.getTeacher());
            aClass.setPoint(apply1.getPoint());
            aClass.setNumber(apply1.getNumber());
            aClass.setTime(apply1.getTime());

            aClass.setSelected(0);
            classMapper.insert(aClass);
        }
        return RespBean.success("批准成功");
    }

    @Override
    public RespBean refuse(Apply apply, String name) {
        Apply apply1 = applyMapper.selectById(apply.getId());
        if (apply1==null){
            return RespBean.error("申请不存在");
        }
        apply1.setDealreason(apply.getDealreason());
        apply1.setStatus(2);
        apply1.setDealtime(LocalDateTime.now());
        apply1.setDealperson(name);
        applyMapper.updateById(apply1);
        return RespBean.success("拒绝成功");
    }

    @Override
    public RespBean addapply(Apply apply) {
        apply.setStarttime(LocalDateTime.now());
        apply.setEndtime(LocalDateTime.now().plusDays(7));
        apply.setApplytime(LocalDateTime.now());
        apply.setStatus(0);
        applyMapper.insert(apply);
        return RespBean.success("申请成功");

    }

}
