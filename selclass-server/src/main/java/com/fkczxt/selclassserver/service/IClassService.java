package com.fkczxt.selclassserver.service;

import com.fkczxt.selclassserver.pojo.Class;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fkczxt.selclassserver.pojo.RespBean;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fk
 * @since 2023-01-18
 */
public interface IClassService extends IService<Class> {
   List<Class> getSelClass(Long id);
   List<Class> getClasses();
}
