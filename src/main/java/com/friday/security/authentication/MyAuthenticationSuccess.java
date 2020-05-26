package com.friday.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆成功处理
 * @author Administrator
 */
@Component
@Slf4j
public class MyAuthenticationSuccess implements AuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("成功登陆");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setContentType("application/json;charset=UTF-8");
        // 200
        response.setStatus(HttpStatus.OK.value());
        // authentication: principal  authoritizes  authenticated=true
        response.getWriter().write(objectMapper.writeValueAsString(authentication));
    }
}
