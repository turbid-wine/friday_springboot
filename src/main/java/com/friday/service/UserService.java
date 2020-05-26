package com.friday.service;

import com.friday.base.result.Result;
import com.friday.dto.UserDto;
import com.friday.entity.ParamsSearchEntity;
import com.friday.entity.SysUser;
import java.util.List;
import java.util.Map;

public interface UserService {

    List<SysUser> selectUserInfo( Map<String,Object> params);

    int getUserCount( Map<String,Object> params);

    int saveUser(SysUser user);

    SysUser getUserByName(String userName);

    SysUser getUserByPhone(String telPhone);

    SysUser getUserEmail(String email);

    UserDto getInfoById(Integer id);

    Result updateUser(UserDto userDto);

    Result deleteUserById(Integer id);

    Result saveUserRole(UserDto userDto);
}
