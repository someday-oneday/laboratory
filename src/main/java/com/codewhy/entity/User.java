package com.codewhy.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: laboratory
 * @description: 用户类
 * @author: CodeWhy
 * @create: 2021-06-01 22:18
 **/
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String mobile;
    private Integer status;
    private Date gmtCreate;
    private Date gmtModified;
    private Role sysRole;
    private Serializable token;
    private String remark;
    private Integer flag; //0 true 1 false
}
