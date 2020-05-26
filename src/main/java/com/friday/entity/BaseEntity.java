package com.friday.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class BaseEntity<ID extends Serializable> implements Serializable {
    private static final long serialVersionUID = 2170929946337604262L;
    private ID id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date gmtCreate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date gmtModify;
}
