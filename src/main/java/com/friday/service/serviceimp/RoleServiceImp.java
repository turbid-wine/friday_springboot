package com.friday.service.serviceimp;

import com.friday.base.result.ResCode;
import com.friday.base.result.Result;
import com.friday.dto.RoleDto;
import com.friday.entity.SysRole;
import com.friday.entity.SysUserRole;
import com.friday.mapper.RoleMapper;
import com.friday.mapper.RolePermissionMapper;
import com.friday.mapper.UserRoleMapper;
import com.friday.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public Result getRoleAll() {
        List<SysRole> data =roleMapper.getRoleAll();
        return Result.getResult(ResCode.SUCCESS.getCode(),ResCode.SUCCESS.getMsg(),data);
    }

    @Override
    public Result getRoleList(Map<String,Object> params) {
        int count=roleMapper.getRoleCount(params);
        List<SysRole> data=roleMapper.getRoleList(params);
        return Result.getResult(ResCode.SUCCESS.getCode(),ResCode.SUCCESS.getMsg(),count,data);
    }

    @Override
    public Result saveRole(RoleDto roleDto) {
        // 1、先存储role表
        roleMapper.save(roleDto);
        List<Integer> permissionIds=roleDto.getPermissionIds();
        log.info(getClass().getName()+"------permissionIds:"+permissionIds);
        // 移除0 permissionIds从1开始
        permissionIds.remove(0);
        // 保存角色对应得权限
        if (!CollectionUtils.isEmpty(permissionIds)){
            rolePermissionMapper.save(roleDto.getId(),permissionIds);
        }
        return Result.getResult(ResCode.SUCCESS.getCode(),ResCode.SUCCESS.getMsg());
    }

    @Override
    public SysRole getRoleInfoById(Integer id) {
        return roleMapper.getRoleById(id);
    }

    @Override
    public Result updateRole(RoleDto roleDto) {
        List<Integer> permissionIds=roleDto.getPermissionIds();
        permissionIds.remove(0);
        // 1、先删除sys_role_permission表中的权限
        rolePermissionMapper.deleteByRoleId(roleDto.getId());
        // 2、再添加之后更改的权限
        if (!CollectionUtils.isEmpty(permissionIds)){
            rolePermissionMapper.save(roleDto.getId(),permissionIds);
        }
        // 3、最后修改角色信息
        int num=roleMapper.update(roleDto);
        if (num>0){
            return Result.getResult(ResCode.SUCCESS.getCode(),ResCode.SUCCESS.getMsg());
        }else {
            return Result.getResult(ResCode.FAIL.getCode(),ResCode.FAIL.getMsg());
        }
    }

    @Override
    public Result deleteById(Integer id) {
        // 1、查询role角色是否有用户关联
        List<SysUserRole> data=userRoleMapper.selectByRoleId(id);
        if (data.size()<=0){
            // 没有直接删除 权限表与角色表级连接
            roleMapper.deleteById(id);
            return Result.getResult(ResCode.SUCCESS.getCode(),ResCode.SUCCESS.getMsg());
        }else {
            return Result.getResult(ResCode.USER_ROLE_NO_CLEAR.getCode(),ResCode.USER_ROLE_NO_CLEAR.getMsg());
        }
    }
}
