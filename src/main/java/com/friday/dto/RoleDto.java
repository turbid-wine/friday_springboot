package com.friday.dto;

import com.friday.entity.SysRole;
import lombok.Data;

import java.util.List;

@Data
public class RoleDto extends SysRole {
    private static final long serialVersionUID = -3729407211226297719L;

    private List<Integer> permissionIds;
}
