package com.codewhy.dao;

import com.codewhy.pojo.Sys_lab;
import com.codewhy.pojo.Sys_lesson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Sys_LesDao {
    /**
     * 添加课程
     * @param sysLesson
     */
    int addLes(Sys_lesson sysLesson);

    /**
     * 查询当前课程是否有预约记录
     * @param lessonWeek
     * @param lessonName
     * @param lessonOder
     * @return
     */
    int queryLabs(@Param("lessonWeek") Integer lessonWeek,@Param("lessonName") String lessonName,@Param("lessonOder")String lessonOder );

    /**
     * 查询可预约的实验室
     * @param lessonName 课程名字
     */
    List<Sys_lab> queryLab(@Param("lessonName")String lessonName,@Param("count")Integer count);

    int addSubscribe(@Param("labID")Integer labID,@Param("lesID")Integer lesID);

    /**
     * 修改实验室预约状态
     * @param labID
     * @return
     */
    int updateLabStatus(@Param("labID")Integer labID);

    /**
     * 修改当前课程预约状态
     * @param labID
     * @return
     */
    int updateLesStatus(@Param("lesID")Integer lesID);

    /**
     * 查询所有课程
     * @param name
     * @return
     */
    List<Sys_lesson> queryLessons(@Param("name")String name);

}
