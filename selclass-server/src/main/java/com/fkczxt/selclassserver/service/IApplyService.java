package com.fkczxt.selclassserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fkczxt.selclassserver.pojo.Apply;
import com.fkczxt.selclassserver.pojo.RespBean;

public interface IApplyService extends IService<Apply> {
    RespBean delclass(Apply apply);

    RespBean getapplylist(Long id,String type);

    RespBean permit(Apply apply, String name);

    RespBean refuse(Apply apply, String name);

    RespBean addapply(Apply apply);
}
