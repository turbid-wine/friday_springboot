package com.friday.mapper;

import com.friday.dto.RoleDto;
import com.friday.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface RoleMapper {

    List<SysRole> getRoleAll();

    List<SysRole> getRoleList(Map<String, Object> params);

    int getRoleCount(Map<String,Object> params);

    int save(SysRole sysRole);

    SysRole getRoleById(Integer id);

    int update(RoleDto roleDto);

    int deleteById(Integer roleId);
}
