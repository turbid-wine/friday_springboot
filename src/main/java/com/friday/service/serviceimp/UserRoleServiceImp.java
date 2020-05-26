package com.friday.service.serviceimp;

import com.friday.mapper.UserRoleMapper;
import com.friday.service.UserRoleService;
import com.friday.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImp implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserService userService;

}
