package com.codewhy.controller;

import com.alibaba.fastjson.JSONObject;
import com.codewhy.entity.Lab;
import com.codewhy.entity.Lesson;

import com.codewhy.service.LabService;
import com.codewhy.service.LessonService;
import com.codewhy.vo.ResultVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class LessonController {

    @Autowired
    private LessonService lessonService;
    @Autowired
    private LabService labService;

    @RequiresRoles("普通用户")
    @PostMapping("/addLes")
    @ApiOperation("添加课程")
    public ResponseEntity<ResultVO> doAddLes(@RequestBody Lesson sysLesson){
        int i = lessonService.addLes(sysLesson);
        return new ResultVO().success("添加成功",i);
    }
    @RequiresRoles("普通用户")
    @PostMapping("/queryLabs")
    @ApiOperation("查询可预约的实验室")
    public ResponseEntity<ResultVO> doQueryLab(@RequestBody JSONObject jsonObject){
        String lessonWeek = (String) jsonObject.get("lessonWeek");
        String lessonName = (String) jsonObject.get("lessonName");
        String lessonOrder = (String) jsonObject.get("lessonOrder");
        Integer count = Integer.parseInt((String) jsonObject.get("count"));
        List<Lab> labs = lessonService.queryLab(Integer.valueOf(lessonWeek), lessonName, lessonOrder,count);
        return new ResultVO().success("查询成功", labs);
    }
    @RequiresRoles("普通用户")
    @ApiOperation("实验市预约")
    @PostMapping("/subscribe")
    public ResponseEntity<ResultVO> doSubscribe(@RequestBody  JSONObject jsonObject){
        Integer labID = Integer.parseInt((String)jsonObject.get("labID"));
        Integer lesID = Integer.parseInt((String)jsonObject.get("lesID"));
        labService.addSubscribe(labID,lesID);
        return new ResultVO().success("预约成功",null);
    }
    @RequiresRoles("普通用户")
    @ApiOperation("查询所有课程")
    @GetMapping("/queryLessons")
    public ResponseEntity<ResultVO> doQueryLessons(String name){
        List<Lesson> lessons = lessonService.queryLesS(name);
        return new ResultVO().success("查询成功", lessons);
    }
}
