package com.codewhy.entity;

import lombok.Data;

import java.util.Date;

//课程
@Data
public class Lesson {
    private  Integer id;            //课程id
    private  Integer lessonOrder;   //第几节课
    private  Integer lessonWeek;    //第几周
    private String lessonName;      //课程名称
    private Integer lessonCount;    //学生数量
    private String  lessonTeacher;  //授课老师
    private Date gmtCreate;         //创建时间
    private Date gmtModifide;       //修改时间
    private Integer status;         //课程状态
}
