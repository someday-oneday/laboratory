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

    /**
     * 查询可以预约的实验室
     * @param lessonWeek 第几周
     * @param lessonName 课程名字
     * @param lessonOder 第几节课
     */

    public List<Lab> queryLab(Integer lessonWeek, String lessonName, String lessonOder, Integer count) {
//        int i = sys_lesDao.queryLabs(lessonWeek, lessonName, lessonOder);
//        if(i == 0 ){
            return lessonMapper.queryLab(lessonName, count);
//        }else {
//            throw new ServiceException("当前课程实验室已被预约");
//        }
    }


    public List<Lesson> queryLesS(String name) {
        return  lessonMapper.queryLessons(name);
    }
}
