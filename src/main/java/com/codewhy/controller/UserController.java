package com.codewhy.controller;

import com.alibaba.fastjson.JSONObject;
import com.codewhy.entity.User;

import com.codewhy.service.UserService;
import com.codewhy.vo.ResultVO;
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

@RestController
@Slf4j
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @RequiresRoles("系统管理员")
    @PostMapping("/addUser")
    @ApiOperation("添加教师用户")
    public ResponseEntity<ResultVO> doAddUser(@RequestBody User sysUser){
        Integer integer = userService.addUser(sysUser);
        return new ResultVO().success("添加成功",integer);
    }
    @RequiresRoles("系统管理员")
    @GetMapping("/queryTeacher")
    @ApiOperation("查询教师")
    public ResponseEntity<ResultVO> queryTeacher(){
        List<User> _users = userService.queryTeachers();
        return new ResultVO().success("查询成功", _users);
    }
    @RequiresRoles("系统管理员")
    @PostMapping("/updateTeacher")
    @ApiOperation("修改教师信息")
    public ResponseEntity<ResultVO> doUpdateTeacher(@RequestBody JSONObject jsonObject){
        Integer id = Integer.parseInt((String) jsonObject.get("id"));
        Integer status = Integer.parseInt((String)jsonObject.get("status"));
        String mobile = (String)jsonObject.get("mobile");
        String username = (String)jsonObject.get("username");
        String remark = (String)jsonObject.get("remark");
        Integer integer = userService.updateTeacher(id, username, mobile, status, remark);
        return new ResultVO().success("修改成功",integer);
    }

    @RequiresRoles("系统管理员")
    @PostMapping("/deleteTeacher")
    @ApiOperation("删除教师")
    public ResponseEntity<ResultVO> doDeleteTeacher(@RequestBody JSONObject jsonObject){
        int id = Integer.parseInt((String) jsonObject.get("id"));
        int i = userService.deleteTeacher(id);
        return new ResultVO().success("删除成功",i);
    }

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
        User user =(User) SecurityUtils.getSubject().getPrincipal();
        user.setToken(id);
        log.info("sessionid:"+id);
        log.info("登录成功");
        return new ResultVO().success("登录成功",user);
    }

}
