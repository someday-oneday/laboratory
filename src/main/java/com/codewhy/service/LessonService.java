package com.codewhy.service;

import com.codewhy.mapper.LessonMapper;
import com.codewhy.entity.Lab;
import com.codewhy.entity.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {
    @Autowired
    private LessonMapper lessonMapper;

    public int addLes(Lesson sysLesson) {
        return lessonMapper.addLes(sysLesson);
    }

    //查询可以预约的实验室
    public List<Lab> queryLab(Integer lessonWeek, String lessonName, String lessonOder, Integer count) {
            return lessonMapper.queryLab(lessonName, count);
    }

    //基于课程名查询课程
    public List<Lesson> queryLesS(String name) {
        return  lessonMapper.queryLessons(name);
    }
}
