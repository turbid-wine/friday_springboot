package com.friday.mapper;

import com.friday.entity.SysRolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RolePermissionMapper {
    int save(@Param("roleId") Integer id, @Param("permissionIds") List<Integer> permissionIds);

    int deleteByRoleId(Integer id);

    List<SysRolePermission> selectByRoleId(Integer roleId);
}
