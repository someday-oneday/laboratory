package com.codewhy.controller;

import com.alibaba.fastjson.JSONObject;
import com.codewhy.common.util.JsonResult;
import com.codewhy.pojo.Sys_User;
import com.codewhy.service.Sys_UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @program: laboratory
 * @description: 用户类控制器
 * @author: CodeWhy
 * @create: 2021-06-01 22:21
 **/

@RestController
@Slf4j
@CrossOrigin
public class Sys_UserController {
    @Autowired
    private Sys_UserService userService;

    /**
     * 添加教师
     * @param sysUser 教师用户对象
     * @return
     */
    @RequiresRoles("系统管理员")
    @PostMapping("/addUser")
    @ApiOperation("添加教师用户")
    public ResponseEntity<JsonResult> doAddUser(@RequestBody Sys_User sysUser){
        Integer integer = userService.addUser(sysUser);
        return new JsonResult().success("添加成功",integer);
    }
    @RequiresRoles("系统管理员")
    @GetMapping("/queryTeacher")
    @ApiOperation("查询教师")
    public ResponseEntity<JsonResult> queryTeacher(){
        List<Sys_User> sys_users = userService.queryTeachers();
        return new JsonResult().success("查询成功",sys_users);
    }
    @RequiresRoles("系统管理员")
    @PostMapping("/updateTeacher")
    @ApiOperation("修改教师信息")
    public ResponseEntity<JsonResult> doUpdateTeacher(@RequestBody JSONObject jsonObject){
        Integer id = Integer.parseInt((String) jsonObject.get("id"));
        Integer status = Integer.parseInt((String)jsonObject.get("status"));
        String mobile = (String)jsonObject.get("mobile");
        String username = (String)jsonObject.get("username");
        String remark = (String)jsonObject.get("remark");
        Integer integer = userService.updateTeacher(id, username, mobile, status, remark);
        return new JsonResult().success("修改成功",integer);
    }

    /**
     * 登录系统
     * @param
     * @return
     */
    @ApiOperation("登录系统")
    @PostMapping("/login")
    public ResponseEntity doLogin(@RequestBody Map map){
        // 获取登录认证主体对象
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                (String) map.get("mobile"),
                (String) map.get("password")
        );

        subject.login(usernamePasswordToken);
        //或去当前shiro session管理器中的seesion id
        Serializable id = SecurityUtils.getSubject().getSession().getId();
        Sys_User user =(Sys_User) SecurityUtils.getSubject().getPrincipal();
        user.setToken(id);
        log.info("sessionid:"+id);
        log.info("登录成功");
        return new JsonResult().success("登录成功",user);
    }

    @RequiresRoles("系统管理员")
    @PostMapping("/deleteTeacher")
    @ApiOperation("删除教师")
    public ResponseEntity<JsonResult> doDeleteTeacher(@RequestBody JSONObject jsonObject){
        int id = Integer.parseInt((String) jsonObject.get("id"));
        int i = userService.deleteTeacher(id);
        return new JsonResult().success("删除成功",i);
    }
}
