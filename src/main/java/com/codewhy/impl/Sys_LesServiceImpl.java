package com.codewhy.impl;

import com.codewhy.common.web.ServiceException;
import com.codewhy.dao.Sys_LesDao;
import com.codewhy.pojo.Sys_lab;
import com.codewhy.pojo.Sys_lesson;
import com.codewhy.service.Sys_LesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Sys_LesServiceImpl implements Sys_LesService {
    @Autowired
    private Sys_LesDao sys_lesDao;
    @Override
    public int addLes(Sys_lesson sysLesson) {
        return sys_lesDao.addLes(sysLesson);
    }

    /**
     * 查询可以预约的实验室
     * @param lessonWeek 第几周
     * @param lessonName 课程名字
     * @param lessonOder 第几节课
     */
    @Override
    public List<Sys_lab> queryLab(Integer lessonWeek, String lessonName, String lessonOder,Integer count) {
//        int i = sys_lesDao.queryLabs(lessonWeek, lessonName, lessonOder);
//        if(i == 0 ){
            return sys_lesDao.queryLab(lessonName, count);
//        }else {
//            throw new ServiceException("当前课程实验室已被预约");
//        }
    }

    @Override
    public List<Sys_lesson> queryLesS(String name) {
        return  sys_lesDao.queryLessons(name);
    }
}
