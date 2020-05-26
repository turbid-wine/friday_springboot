package com.friday.security.authentication;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 没有相应的权限使其跳转403页面
 * @Author Haley
 * @Date 2020/3/18 13:40
 **/
@Component
public class MyAuthenticationAccessDenied implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.sendRedirect("/403.html");
    }
}
