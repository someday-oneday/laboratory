package com.codewhy.service;

import com.codewhy.pojo.Sys_User;
import com.codewhy.pojo.Sys_lab;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Sys_UserService {
    Integer addUser(Sys_User sysUser);

    List<Sys_User> queryTeachers();

    Integer updateTeacher(Integer id, String username, String mobile,
                          Integer status, String remark);

    int deleteTeacher(Integer id);


}
