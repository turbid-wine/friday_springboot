package com.friday.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.friday.entity.SysPermission;
import com.friday.entity.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 *处理用户登陆校验
 * @author Administrator
 */
@Data
public class LoginUser extends SysUser implements UserDetails {
    private static final long serialVersionUID = 7696743383319082691L;

    private List<SysPermission> permissions;

    /**
     * 授予权限
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 将账户对应的权限过滤掉并存放在authorizes中
        return permissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
                .map(p -> new SimpleGrantedAuthority(p.getPermission())).collect(Collectors.toList());
    }

    /**
     * 账户是否过期
      * @return
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否锁定
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return getStatus()!=Status.LOCKED;
    }

    /**
     * 密码是否过期
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账户是否激活
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return getPassWord();
    }

    @Override
    public String getUsername() {
        return getUserName();
    }

}
