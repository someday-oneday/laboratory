package com.codewhy.entity;

import lombok.Data;

//实验室课程
@Data
public class LabLesson {
    private Integer id;
    private Integer lobId;   //实验室id
    private Integer lesId;   //课程id

}
