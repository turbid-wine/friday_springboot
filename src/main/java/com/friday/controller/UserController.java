package com.friday.controller;

import com.friday.base.result.ResCode;
import com.friday.base.result.Result;
import com.friday.base.result.TableParamRequest;
import com.friday.base.util.ParamUtil;
import com.friday.dto.UserDto;
import com.friday.entity.ParamsSearchEntity;
import com.friday.entity.SysUser;
import com.friday.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    Map<String,Object> map=null;

    /**
     *  只需要加上下面这段即可，注意不能忘记注解
     */
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        // CustomDateEditor为自定义日期编辑器
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    /**
     * table表数据查询、模糊条件查询
     * @param request
     * @param pse
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('sys:user:query')")
    @ApiOperation(value = "分页获取用户信息",notes = "分页获取用户信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "request",value = "分页查询实体类",required = false)
    ,@ApiImplicitParam(name = "pse",value = "分页模糊查询实体类",required = false)})
    public Result selectUserInfo(TableParamRequest request,ParamsSearchEntity pse){
        map=ParamUtil.getParams(request,pse);
        Integer count=userService.getUserCount(map);
        List<SysUser> data=userService.selectUserInfo(map);
        if (!StringUtils.isEmpty(data)){
            return Result.getResult(ResCode.TABLE_SUCCESS.getCode(),ResCode.TABLE_SUCCESS.getMsg(),count,data);
        }else {
            return Result.getResult(ResCode.FAIL.getCode(),ResCode.FAIL.getMsg());
        }
    }

    @GetMapping("/add")
    @PreAuthorize("hasAnyAuthority('sys:user:add')")
    public String addUserView(){
        return "user/user-add";
    }

    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('sys:user:add')")
    public Result addUser(UserDto userDto) {
        if (!checkResult(userDto)){
            return Result.getResult((Integer) map.get("code"),(String) map.get("msg"));
        }else {
            return userService.saveUserRole(userDto);
        }
    }

    @GetMapping("/edit")
    @PreAuthorize("hasAnyAuthority('sys:user:edit')")
    public ModelAndView editUserView(Integer id){
        UserDto userDto=userService.getInfoById(id);
        return new ModelAndView("user/user-edit")
                .addObject(userDto);
    }

    @PostMapping("/edit")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('sys:user:edit')")
    public Result updateUser(UserDto userDto){
        if (!checkResult(userDto)){
            return Result.getResult((Integer) map.get("code"),(String) map.get("msg"));
        }else {
            return userService.updateUser(userDto);
        }
    }

    @GetMapping("/delete")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('sys:user:delete')")
    public Result deleteUserById(String id){
        return userService.deleteUserById(Integer.parseInt(id));
    }


    /**
     * 检查数据库是否数据是否重复
     * @param userDto
     * @return
     */
    private boolean checkResult(UserDto userDto){
        SysUser sysUser=userService.getUserByName(userDto.getUserName());
            map=new HashMap<>();
        if (!StringUtils.isEmpty(sysUser) && !(sysUser.getId()).equals(userDto.getId())){
            map.put("code",ResCode.USERNAME_REPEAT.getCode());
            map.put("msg",ResCode.USERNAME_REPEAT.getMsg());
            return false;
        }
        sysUser=userService.getUserByPhone(userDto.getTelPhone());
        if (!StringUtils.isEmpty(sysUser) && !(sysUser.getId()).equals(userDto.getId())){
            map.put("code",ResCode.PHONE_REPEAT.getCode());
            map.put("msg",ResCode.PHONE_REPEAT.getMsg());
            return false;
        }
        sysUser=userService.getUserEmail(userDto.getEmail());
        if (!StringUtils.isEmpty(sysUser) && !(sysUser.getId()).equals(userDto.getId())){
            map.put("code",ResCode.EMAIL_REPEAT.getCode());
            map.put("msg",ResCode.EMAIL_REPEAT.getMsg());
            return false;
        }
        return true;
    }
}
