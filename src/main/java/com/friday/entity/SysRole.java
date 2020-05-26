package com.friday.entity;

import lombok.Data;

@Data
public class SysRole extends BaseEntity<Integer> {
    private static final long serialVersionUID = 5484921163708263080L;

    private String name;
    private String description;
}
