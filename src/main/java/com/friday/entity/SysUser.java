package com.friday.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseEntity<Integer>{
    private static final long serialVersionUID = -3710742487036813634L;

    private String userName;
    private String passWord;
    private String nickname;
    private String headImgUrl;
    private Integer sex;
    private Date birthday;
    private String telPhone;
    private String email;
    private Integer status;
    private String intro;

    public interface Status{
        int DISABLED=0;
        int VALID=1;
        int LOCKED=2;
    }


}
