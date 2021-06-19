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
    @ApiOperation("查询所有课程")
    @GetMapping("/queryLessons")
    public ResponseEntity<ResultVO> doQueryLessons(String name){
        List<Lesson> lessons = lessonService.queryLesS(name);
        return new ResultVO().success("查询成功", lessons);
    }
}
