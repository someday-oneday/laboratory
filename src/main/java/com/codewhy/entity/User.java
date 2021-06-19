package com.codewhy.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//用户
@Data
public class User {
    private Integer id;           //用户id
    private String username;      //用户名
    private String password;      //密码
    private String mobile;        //手机号
    private Integer status;       //状态   1-启用 0-禁用
    private Date gmtCreate;       //创建时间
    private Date gmtModified;     //修改时间
    private Role sysRole;         //角色名称
    private Serializable token;   //token
    private String remark;        //职称
    private Integer flag;         //是否为教师   1-是 0-否
}
