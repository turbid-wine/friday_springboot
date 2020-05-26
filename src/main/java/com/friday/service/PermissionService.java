package com.friday.service;

import com.friday.base.result.Result;
import com.friday.entity.SysPermission;

public interface PermissionService {
    Result getAllPermission();

    Result listById(Integer id);

    Result getMenuAll();

    Result savePermission(SysPermission sysPermission);

    SysPermission selectPermissionById(Integer id);

    Result updatePermission(SysPermission sysPermission);

    Result deleteById(Integer id);

    /**
     * fetch sys_permission data by userId
     *
     * @param id
     * @return
     */
    Result getMenuById(Integer id);
}
