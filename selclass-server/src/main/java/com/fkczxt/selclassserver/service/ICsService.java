package com.fkczxt.selclassserver.service;

import com.fkczxt.selclassserver.pojo.Cs;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fkczxt.selclassserver.pojo.RespBean;

import javax.servlet.http.HttpServletRequest;

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
    RespBean delclass(Cs cs);
    RespBean beforesel(Cs cs);

    String getfraction(Integer id, Long sid);
}
