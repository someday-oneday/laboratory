package com.codewhy.controller;


import com.alibaba.fastjson.JSONObject;
import com.codewhy.entity.Lab;
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
public class LabController {
    @Autowired
    private LabService labService;

    @Autowired
    private LessonService lessonService;

    @RequiresRoles("系统管理员")
    @PostMapping("/addLab")
    @ApiOperation("添加实验室")
    public ResponseEntity<ResultVO> doAddLab(@RequestBody Lab sysLab){
        int i = labService.addLab(sysLab);
        return new ResultVO().success("添加成功",i);
    }

    @GetMapping("/queryLab")
    @ApiOperation("查询实验室")
    public ResponseEntity<ResultVO> doQueryLab(String lab){
        List<Lab> labs = labService.queryLabs(lab);
        return new ResultVO().success("查询成功", labs);
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
    @ApiOperation("实验室预约")
    @PostMapping("/subscribe")
    public ResponseEntity<ResultVO> doSubscribe(@RequestBody  JSONObject jsonObject){
        Integer labID = Integer.parseInt((String)jsonObject.get("labID"));
        Integer lesID = Integer.parseInt((String)jsonObject.get("lesID"));
        labService.addSubscribe(labID,lesID);
        return new ResultVO().success("预约成功",null);
    }
}
