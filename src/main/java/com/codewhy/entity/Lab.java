package com.codewhy.entity;

import lombok.Data;

import java.util.Date;

//实验室
@Data
public class Lab {
    private Integer id;        //实验室id
    private String lab;        //实验室名称
    private String serail;     //实验室编号
    private Integer count;     //机器数量
    private String remark;     //实验室描述
    private Integer status;    //实验室状态
    private Date gmtCreate;    //创建时间
    private Date gmtModified;  //修改时间
}
