package com.codewhy.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Sys_lesson {
    private  Integer id;
    private  Integer lessonOrder;
    private  Integer lessonWeek;
    private String lessonName;
    private Integer lessonCount;
//    private String lessonBegin;
//    private String lessonEnd;
    private String  lessonTeacher;
    private Date gmtCreate;
    private Date gmtModifide;
    private Integer status;
}
