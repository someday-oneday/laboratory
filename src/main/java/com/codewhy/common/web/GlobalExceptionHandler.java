package com.codewhy.common.web;

import com.codewhy.common.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity GobalExceptionHandler(RuntimeException e){
        e.printStackTrace();
        return new JsonResult().fail(e.getMessage());
    }


    @ResponseBody
    @ExceptionHandler(value= DataAccessException.class)
    public ResponseEntity dataAccessErrorHandler(DataAccessException e){
//        e.printStackTrace();
        return new JsonResult().fail("请单号填写是否准确");
    }

    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public ResponseEntity doHandleShiroException(
            ShiroException e) {
        JsonResult r=new JsonResult();
        r.setCode(500);
        if(e instanceof UnknownAccountException) {
            r.setMsg("账户不存在");
        }else if(e instanceof IncorrectCredentialsException) {
            r.setMsg("密码不正确");
        }else if(e instanceof AuthorizationException) {
            r.setMsg("没有此操作权限");
        }else if(e instanceof LockedAccountException){
            r.setMsg("用户已被禁用");
        }else if (e instanceof IncorrectCredentialsException){
            r.setMsg("该角色被禁用");
        }
        else {
            r.setMsg("系统维护中");
        }
        e.printStackTrace();
        return new JsonResult().fail1(r);
    }
}
