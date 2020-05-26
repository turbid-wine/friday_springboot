package com.friday.mapper;

import com.friday.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper {


    int saveUserRole(SysUserRole sysUserRole);

    int updateUserRole(SysUserRole sysUserRole);

    int deleteUserByUserId(Integer id);

    List<SysUserRole> selectByRoleId(Integer id);
}
