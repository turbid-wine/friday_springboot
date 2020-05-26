package com.friday.entity;

import lombok.Data;

@Data
public class SysUserRole extends BaseEntity{

    private Integer userId;
    private Integer roleId;
}
