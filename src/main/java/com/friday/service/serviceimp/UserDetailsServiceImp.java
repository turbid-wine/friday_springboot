package com.friday.service.serviceimp;

import com.friday.dto.LoginUser;
import com.friday.entity.SysPermission;
import com.friday.entity.SysUser;
import com.friday.mapper.PermissionMapper;
import com.friday.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *处理获取用户信息
 *
 * @author Administrator
 */
@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 前端用户登陆时获取信息
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysUser sysUser=userService.getUserByName(userName);
        if (sysUser==null)
        {
            throw new  AuthenticationCredentialsNotFoundException("用户不存在");
        }else if (sysUser.getStatus()==SysUser.Status.DISABLED)
        {
            throw new DisabledException("该用户已失效");
        }else if (sysUser.getStatus()==SysUser.Status.LOCKED)
        {
            throw new LockedException("用户已锁定，请联系管理员");
        }
        LoginUser loginUser=new LoginUser();
        // 将sysUser的属性值赋值给loginUser对象(共有的属性)
        BeanUtils.copyProperties(sysUser,loginUser);
        List<SysPermission> permissions=permissionMapper.listByUserId(sysUser.getId());
        loginUser.setPermissions(permissions);
        return loginUser;
    }
}
