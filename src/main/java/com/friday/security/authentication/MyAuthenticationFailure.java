package com.friday.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class MyAuthenticationFailure implements AuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        log.info("登陆失败");
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","*");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        Map<String,Object> map=new HashMap<>(16);
        if (e instanceof LockedException){
            map.put("message","账户已锁定，登录失败");
        }else if (e instanceof BadCredentialsException){
            map.put("message","密码输入错误，登录失败");
        }else if (e instanceof DisabledException){
            map.put("message","账户被禁用，登录失败");
        }else if (e instanceof AccountExpiredException){
            map.put("message","账户已过期，登录失败");
        }else if (e instanceof CredentialsExpiredException){
            map.put("message","账户密码过期，登录失败");
        }else if (e instanceof InternalAuthenticationServiceException){
            map.put("message","账户不存在，登录失败");
        } else {
            map.put("message","登录失败");
        }

        response.getWriter().write(objectMapper.writeValueAsString(map));
    }
}
