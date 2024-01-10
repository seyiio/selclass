package com.fkczxt.selclassserver.service;

import com.fkczxt.selclassserver.pojo.RespBean;
import com.fkczxt.selclassserver.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fk
 * @since 2023-01-18
 */
public interface IUserService extends IService<User> {

    RespBean login(String username, String password, HttpServletRequest request);

    User getUserByusername(String username);

    RespBean changeuser(User user);

    RespBean deleteuser(User user);

    RespBean getusers();
}
