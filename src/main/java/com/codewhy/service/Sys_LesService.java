package com.codewhy.service;

import com.codewhy.pojo.Sys_lab;
import com.codewhy.pojo.Sys_lesson;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Sys_LesService {
    int addLes(Sys_lesson sysLesson);

    List<Sys_lab> queryLab(Integer lessonWeek, String lessonName,String lessonOder,Integer count);

    List<Sys_lesson> queryLesS(String name);
}
