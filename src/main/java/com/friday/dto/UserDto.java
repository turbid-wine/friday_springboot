package com.friday.dto;

import com.friday.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends SysUser {
    private static final long serialVersionUID = 7620235745800525152L;

    private Integer roleId;
}
