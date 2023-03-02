package com.fkczxt.selclassserver.service;

import com.fkczxt.selclassserver.pojo.RespBean;
import com.fkczxt.selclassserver.pojo.Time;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fkczxt.selclassserver.pojo.User;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fk
 * @since 2023-03-01
 */
public interface ITimeService extends IService<Time> {
    Time gettime();
    RespBean changeTime(Time time);
}
