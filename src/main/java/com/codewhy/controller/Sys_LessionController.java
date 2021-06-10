package com.codewhy.controller;

import com.alibaba.fastjson.JSONObject;
import com.codewhy.common.util.JsonResult;
import com.codewhy.dao.Sys_LesDao;
import com.codewhy.pojo.Sys_lab;
import com.codewhy.pojo.Sys_lesson;
import com.codewhy.service.Sys_LesService;
import com.codewhy.service.Sys_labService;
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
public class Sys_LessionController {

    @Autowired
    private Sys_LesService sys_lesService;
    @Autowired
    private Sys_labService sys_labService;

    @RequiresRoles("普通用户")
    @PostMapping("/addLes")
    @ApiOperation("添加课程")
    public ResponseEntity<JsonResult> doAddLes(@RequestBody Sys_lesson sysLesson){
        int i = sys_lesService.addLes(sysLesson);
        return new JsonResult().success("添加成功",i);
    }
    @RequiresRoles("普通用户")
    @PostMapping("/queryLabs")
    @ApiOperation("查询可预约的实验室")
    public ResponseEntity<JsonResult> doQueryLab(@RequestBody JSONObject jsonObject){
        String lessonWeek = (String) jsonObject.get("lessonWeek");
        String lessonName = (String) jsonObject.get("lessonName");
        String lessonOrder = (String) jsonObject.get("lessonOrder");
        Integer count = Integer.parseInt((String) jsonObject.get("count"));
        List<Sys_lab> sys_labs = sys_lesService.queryLab(Integer.valueOf(lessonWeek), lessonName, lessonOrder,count);
        return new JsonResult().success("查询成功",sys_labs);
    }
    @RequiresRoles("普通用户")
    @ApiOperation("实验市预约")
    @PostMapping("/subscribe")
    public ResponseEntity<JsonResult> doSubscribe(@RequestBody  JSONObject jsonObject){
        Integer labID = Integer.parseInt((String)jsonObject.get("labID"));
        Integer lesID = Integer.parseInt((String)jsonObject.get("lesID"));
        sys_labService.addSubscribe(labID,lesID);
        return new JsonResult().success("预约成功",null);
    }
    @RequiresRoles("普通用户")
    @ApiOperation("查询所有课程")
    @GetMapping("/queryLessons")
    public ResponseEntity<JsonResult> doQueryLessons(String name){
        List<Sys_lesson> sys_lessons = sys_lesService.queryLesS(name);
        return new JsonResult().success("查询成功",sys_lessons);
    }
}
