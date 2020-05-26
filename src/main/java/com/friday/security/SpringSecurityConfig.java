package com.friday.security;

import com.friday.security.authentication.MyAuthenticationAccessDenied;
import com.friday.security.authentication.MyAuthenticationFailure;
import com.friday.security.authentication.MyAuthenticationSuccess;
import com.friday.security.authentication.MyLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * @author Administrator
 */

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private MyAuthenticationSuccess myAuthenticationSuccess;
    @Autowired
    private MyAuthenticationFailure myAuthenticationFailure;
    @Autowired
    private MyAuthenticationAccessDenied myAuthenticationAccessDenied;
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 用户信息获取、密码校验
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();  // 禁用跨域请求
        http.authorizeRequests()
                .antMatchers("/login.html",
                        "/druid/*",
                        "/static/**",
                        "/treetable-lay/**",
                        "/xadmin/**",
                        "/ztree/**"
                )
                .permitAll()
                .anyRequest()
                .authenticated();
        // 指定登陆页面路径 以及表单提交数据的url
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .successHandler(myAuthenticationSuccess)
                .failureHandler(myAuthenticationFailure)
                .and()
                .logout()
                .permitAll()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(myLogoutSuccessHandler);

        // 解决'X-Frame-Options'
        http.headers().frameOptions().sameOrigin();
        //异常处理
        http.exceptionHandling().accessDeniedHandler(myAuthenticationAccessDenied);
    }
}
