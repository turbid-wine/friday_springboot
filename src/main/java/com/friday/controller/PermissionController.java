package com.friday.controller;

import com.friday.base.result.Result;
import com.friday.entity.SysPermission;
import com.friday.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("permission")
@Slf4j
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/listAllPermission")
    @ResponseBody
    public Result listAllPermission(){
        return  permissionService.getAllPermission();
    }

    /**
     * 根据roleId获取对应得所有权限
     * @param id
     * @return
     */
    @GetMapping("/listAllPermissionByRoleId")
    @ResponseBody
    public Result listAllPermissionByRoleId(Integer id){
        log.info(getClass().getName()+"=======param======"+id);
        return  permissionService.listById(id);
    }

    @GetMapping("/menuAll")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('sys:menu:query')")
    public Result getMenuAll(){
        return permissionService.getMenuAll();
    }

    @GetMapping("/add")
    @PreAuthorize("hasAnyAuthority('sys:menu:add')")
    public String addPermissionView(Model model){
        model.addAttribute("sysPermission",new SysPermission());
        return "permission/permission-add";
    }

    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('sys:menu:add')")
    public Result addPermission(@RequestBody SysPermission sysPermission){
        return permissionService.savePermission(sysPermission);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('sys:menu:edit')")
    public ModelAndView editPermissionView(Integer id){
        return new ModelAndView("permission/permission-add")
                .addObject("sysPermission",permissionService.selectPermissionById(id));
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('sys:menu:edit')")
    public Result editPermission(@RequestBody SysPermission sysPermission){
       return permissionService.updatePermission(sysPermission);
    }

    @GetMapping("/delete")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('sys:menu:delete')")
    public Result deletePermission(Integer id){
        log.info(getClass().getName()+"=====id===="+id);
        return permissionService.deleteById(id);
    }

    @GetMapping("/menu")
    @ResponseBody
    public Result getMenu(Integer userId){
        log.info(getClass().getName()+"=====id===="+userId);
        return permissionService.getMenuById(userId);
    }
}
