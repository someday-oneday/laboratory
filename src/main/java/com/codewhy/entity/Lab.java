package com.codewhy.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Lab {
    private Integer id;
    private String lab;
    private String serail;
    private Integer count;
    private String remark;
    private Integer status;
    private Date gmtCreate;
    private Date gmtModified;
}
