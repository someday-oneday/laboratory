package com.codewhy.mapper;

import com.codewhy.entity.Lab;
import com.codewhy.entity.Lesson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LessonMapper {
    //添加课程
    int addLes(Lesson sysLesson);

    /**
     * 查询当前课程是否有预约记录
     * @param lessonWeek
     * @param lessonName
     * @param lessonOder
     * @return
     */
    //int queryLabs(@Param("lessonWeek") Integer lessonWeek,@Param("lessonName") String lessonName,@Param("lessonOder")String lessonOder );

    //查询可预约的实验室
    List<Lab> queryLab(@Param("lessonName")String lessonName, @Param("count")Integer count);

    int addSubscribe(@Param("labID")Integer labID,@Param("lesID")Integer lesID);

    //修改实验室预约状态
    int updateLabStatus(@Param("labID")Integer labID);

    //修改当前课程预约状态
    int updateLesStatus(@Param("lesID")Integer lesID);

    //基于课程名查询课程
    List<Lesson> queryLessons(@Param("name")String name);

}
