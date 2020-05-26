package com.friday.service;

import com.friday.base.result.Result;
import com.friday.dto.RoleDto;
import com.friday.entity.SysRole;

import java.util.Map;

public interface RoleService {
    Result getRoleAll();

    Result getRoleList(Map<String,Object> params);

    Result saveRole(RoleDto roleDto);

    SysRole getRoleInfoById(Integer id);

    Result updateRole(RoleDto roleDto);

    Result deleteById(Integer id);
}
