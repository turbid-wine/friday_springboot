package com.friday.mapper;

import com.friday.dto.UserDto;
import com.friday.entity.ParamsSearchEntity;
import com.friday.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    List<SysUser> selectUserInfo( Map<String,Object> params);

    int getUserCount( Map<String,Object> params);

    int saveUser(SysUser user);

    SysUser getUserByName(String userName);

    SysUser getUserByPhone(String telPhone);

    SysUser getUserByEmail(String email);

    UserDto getInfoById(Integer id);

    int updateUser(UserDto userDto);

    int deleteUserById(Integer id);
}
