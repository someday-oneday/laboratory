package com.codewhy.controller;

import com.codewhy.common.util.JsonResult;
import com.codewhy.pojo.Sys_lab;
import com.codewhy.service.Sys_labService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class Sys_labController {
    @Resource
    private Sys_labService sys_labService;

    @RequiresRoles("系统管理员")
    @PostMapping("/addLab")
    @ApiOperation("添加实验室")
    public ResponseEntity<JsonResult> doAddLab(@RequestBody Sys_lab sysLab){
        int i = sys_labService.addLab(sysLab);
        return new JsonResult().success("添加成功",i);
    }

    @GetMapping("/queryLab")
    @ApiOperation("查询实验室")
    public ResponseEntity<JsonResult> doQueryLab(String lab){
        List<Sys_lab> sys_labs = sys_labService.queryLabs(lab);
        return new JsonResult().success("查询成功",sys_labs);
    }
}
