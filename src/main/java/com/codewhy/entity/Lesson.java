package com.codewhy.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Lesson {
    private  Integer id;
    private  Integer lessonOrder;
    private  Integer lessonWeek;
    private String lessonName;
    private Integer lessonCount;
    private String  lessonTeacher;
    private Date gmtCreate;
    private Date gmtModifide;
    private Integer status;
}
