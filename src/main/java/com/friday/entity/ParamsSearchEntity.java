package com.friday.entity;

import lombok.Data;

/**
 * table表多条件模糊查询接受对象
 */
@Data
public class ParamsSearchEntity {
    private String userName;
    private String roleName;
    private String startTime;
    private String endTime;
}
