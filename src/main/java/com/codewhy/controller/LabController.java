package com.codewhy.controller;


import com.codewhy.entity.Lab;
import com.codewhy.service.LabService;
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
}
