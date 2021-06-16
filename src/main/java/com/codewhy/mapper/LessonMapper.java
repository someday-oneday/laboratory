package com.codewhy.mapper;

import com.codewhy.entity.Lab;
import com.codewhy.entity.Lesson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LessonMapper {
    /**
     * 添加课程
     * @param sysLesson
     */
    int addLes(Lesson sysLesson);

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
    List<Lab> queryLab(@Param("lessonName")String lessonName, @Param("count")Integer count);

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
    List<Lesson> queryLessons(@Param("name")String name);

}
