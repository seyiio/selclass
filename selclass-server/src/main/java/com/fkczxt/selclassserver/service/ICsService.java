package com.fkczxt.selclassserver.service;

import com.fkczxt.selclassserver.pojo.Cs;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fkczxt.selclassserver.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fk
 * @since 2023-01-18
 */
public interface ICsService extends IService<Cs> {
    RespBean selclass(Cs cs);
}
