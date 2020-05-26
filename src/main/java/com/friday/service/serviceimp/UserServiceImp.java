package com.friday.service.serviceimp;

import com.friday.base.result.ResCode;
import com.friday.base.result.Result;
import com.friday.dto.UserDto;
import com.friday.entity.SysUser;
import com.friday.entity.SysUserRole;
import com.friday.mapper.UserMapper;
import com.friday.mapper.UserRoleMapper;
import com.friday.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@Component
@Transactional
public  class UserServiceImp implements UserService {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<SysUser> selectUserInfo( Map<String,Object> params) {
        return userMapper.selectUserInfo(params);
    }

    @Override
    public int getUserCount( Map<String,Object> params) {
        return userMapper.getUserCount(params);
    }

    @Override
    public int saveUser(SysUser user) {
        return userMapper.saveUser(user);
    }

    @Override
    public SysUser getUserByName(String userName) {
        return userMapper.getUserByName(userName);
    }

    @Override
    public SysUser getUserByPhone(String telPhone) {
        return userMapper.getUserByPhone(telPhone);
    }

    @Override
    public SysUser getUserEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public UserDto getInfoById(Integer id) {
        return userMapper.getInfoById(id);
    }

    @Override
    public Result updateUser(UserDto userDto) {
        if (!StringUtils.isEmpty(userDto.getRoleId())){
            userDto.setGmtModify(new Date());
            userMapper.updateUser(userDto);
            SysUserRole sysUserRole=new SysUserRole();
            sysUserRole.setRoleId(userDto.getRoleId());
            sysUserRole.setUserId(userDto.getId());
            sysUserRole.setGmtModify(new Date());
            userRoleMapper.updateUserRole(sysUserRole);
            return Result.getResult(ResCode.SUCCESS.getCode(),ResCode.SUCCESS.getMsg());
        }else {
            return Result.getResult(ResCode.FAIL.getCode(),ResCode.FAIL.getMsg());
        }
    }

    @Override
    public Result deleteUserById(Integer id) {
        if (id!=null){
            int userRoleNum=userRoleMapper.deleteUserByUserId(id);
            if (userRoleNum>0){
                userMapper.deleteUserById(id);
                return Result.getResult(ResCode.SUCCESS.getCode(),ResCode.SUCCESS.getMsg());
            }
        }
        return Result.getResult(ResCode.FAIL.getCode(),ResCode.FAIL.getMsg());
    }

    @Override
    public Result saveUserRole(UserDto userDto) {
        if (userDto.getRoleId()!=null){
            saveUser(userDto);
            SysUserRole sysUserRole=new SysUserRole();
            sysUserRole.setUserId(userDto.getId());
            sysUserRole.setRoleId(userDto.getRoleId());
            userRoleMapper.saveUserRole(sysUserRole);
            return Result.getResult(ResCode.SUCCESS.getCode(),ResCode.SUCCESS.getMsg());

        }
        return Result.getResult(ResCode.FAIL.getCode(),ResCode.FAIL.getMsg());
    }
}
