package com.fkczxt.selclassserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fkczxt.selclassserver.config.security.JwtTokenUtil;
import com.fkczxt.selclassserver.pojo.RespBean;
import com.fkczxt.selclassserver.pojo.User;
import com.fkczxt.selclassserver.mapper.UserMapper;
import com.fkczxt.selclassserver.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fk
 * @since 2023-01-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Override
    public RespBean login(String username, String password, HttpServletRequest request) {
        UserDetails userDetails=userDetailsService.loadUserByUsername(username);
        String a=passwordEncoder.encode("123");
        if(null==userDetails||!passwordEncoder.matches(password,userDetails.getPassword())){
            return RespBean.error("用户名或密码不正确");
        }
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        String token= jwtTokenUtil.generateToken(userDetails);
        Map<String,String> tokenMap=new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);

        return RespBean.success("登录成功",tokenMap);
    }

    @Override
    public User getUserByusername(String username) {

        return userMapper.selectOne(new QueryWrapper<User>().eq("username",username));

    }
}
