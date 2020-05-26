package com.friday.service.serviceimp;

import com.alibaba.fastjson.JSONArray;
import com.friday.base.result.ResCode;
import com.friday.base.result.Result;
import com.friday.entity.SysPermission;
import com.friday.mapper.PermissionMapper;
import com.friday.service.PermissionService;
import com.friday.util.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class PermissionServiceImp implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Result getAllPermission() {
        log.info(getClass().getName()+".getAllPermission()");
        // 查询sys_permission表
        List data=permissionMapper.getPermission();
        JSONArray array=new JSONArray();
        log.info(getClass().getName()+".setPermissionsTree(?,?,?)");
        TreeUtil.setPermissionsTree(0,data,array);
        return Result.getResult(ResCode.SUCCESS.getCode(),ResCode.SUCCESS.getMsg(),array);
    }

    @Override
    public Result listById(Integer id) {
        List<SysPermission> data=permissionMapper.listByRoleId(id);
        return Result.getResult(ResCode.SUCCESS.getCode(),ResCode.SUCCESS.getMsg(),data);
    }

    @Override
    public Result getMenuAll() {
        List<SysPermission> data=permissionMapper.getPermission();

        log.info(getClass().getName()+"====data==="+data);
        return Result.getResult(ResCode.SUCCESS.getCode(),ResCode.SUCCESS.getMsg(),data);
    }

    @Override
    public Result savePermission(SysPermission sysPermission) {

        return (permissionMapper.save(sysPermission) > 0 ) ? Result.success() :Result.failure();
    }

    @Override
    public SysPermission selectPermissionById(Integer id) {
        return  permissionMapper.selectById(id);
    }

    @Override
    public Result updatePermission(SysPermission sysPermission) {

        return (permissionMapper.update(sysPermission)) >0 ? Result.success() : Result.failure();
    }

    @Override
    public Result deleteById(Integer id) {
        return permissionMapper.deleteById(id)>0 ? Result.success():Result.failure();
    }

    @Override
    public Result getMenuById(Integer id) {
        List<SysPermission> data=permissionMapper.listByUserId(id);
        // 1:菜单  2:按钮  把按钮过滤掉返回到list集合
        data=data.stream().filter(sp -> sp.getType().equals(1)).collect(Collectors.toList());
        log.info(getClass().getName()+"======data===="+data);
        JSONArray array=new JSONArray();
        TreeUtil.setPermissionsTree(0,data,array);
        return Result.getResult(ResCode.SUCCESS.getCode(),ResCode.SUCCESS.getMsg(),array);
    }
}
