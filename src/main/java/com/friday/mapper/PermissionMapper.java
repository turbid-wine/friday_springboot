package com.friday.mapper;

import com.friday.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper {
    List<SysPermission> getPermission();

    /**
     * 通过role id获取对应权限
     * @param id
     * @return
     */
    List<SysPermission> listByRoleId(Integer id);

    int save(SysPermission sysPermission);

    SysPermission selectById(Integer id);

    int update(SysPermission sysPermission);

    int deleteById(Integer id);

    /**
     * 通过用户id查找对应的权限
     *
     * @param id
     * @return
     */
    List<SysPermission> listByUserId(Integer id);
}
