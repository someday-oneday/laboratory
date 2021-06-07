package com.codewhy.pojo;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;

@Data
public class Sys_lab {
    private Integer id;
    private String lab;
    private String serail;
    private Integer count;
    private String remark;
    private Integer status;
    private Date gmtCreate;
    private Date gmtModified;
}
