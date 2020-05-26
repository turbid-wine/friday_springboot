package com.friday.controller;

import com.friday.base.result.Result;
import com.friday.base.result.TableParamRequest;
import com.friday.base.util.ParamUtil;
import com.friday.dto.RoleDto;
import com.friday.dto.UserDto;
import com.friday.entity.ParamsSearchEntity;
import com.friday.entity.SysRole;
import com.friday.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("role")
@Slf4j
public class RoleController {

    @Autowired
    private RoleService roleService;


    @GetMapping("/all")
    @ResponseBody
    public Result getAllRole(){
       return roleService.getRoleAll();
    }

    @GetMapping("/list")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('sys:role:query')")
    public Result selectRoleList(TableParamRequest request, ParamsSearchEntity pse){
        log.info("RoleController.selectRoleList===UserName:"+pse.getUserName()+"-StartTime:"+pse.getStartTime()+"-EndTime"+pse.getEndTime());
        Map<String,Object> params= ParamUtil.getParams(request,pse);
        return roleService.getRoleList(params);
    }

    @GetMapping("/add")
    @PreAuthorize("hasAnyAuthority('sys:role:add')")
    public String addRoleView(){
        return "/role/role-add";
    }

    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('sys:role:add')")
    public Result addRole(@RequestBody RoleDto roleDto){
        return roleService.saveRole(roleDto);
    }

    @GetMapping("/edit")
    @PreAuthorize("hasAnyAuthority('sys:role:edit')")
    public ModelAndView editRoleView(Integer id){
        return new ModelAndView("role/role-edit")
                .addObject("sysRole",roleService.getRoleInfoById(id));
    }

    @PostMapping("/edit")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('sys:role:edit')")
    public Result editRole(@RequestBody RoleDto roleDto){
        log.info(getClass().getName()+"======roleDto========"+roleDto);
        return roleService.updateRole(roleDto);
    }

    @GetMapping("/delete")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('sys:role:delete')")
    public Result deleteRole(Integer id){
        log.info(getClass().getName()+"======roleId========"+id);
        return roleService.deleteById(id);
    }
}
