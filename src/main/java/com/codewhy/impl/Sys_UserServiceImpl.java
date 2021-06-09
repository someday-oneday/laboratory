package com.codewhy.impl;

import com.codewhy.common.web.ServiceException;
import com.codewhy.dao.Sys_UserDao;
import com.codewhy.pojo.Sys_User;
import com.codewhy.service.Sys_UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: laboratory
 * @description: 用户业务逻辑类
 * @author: CodeWhy
 * @create: 2021-06-01 22:32
 **/

@Service
@Slf4j
public class Sys_UserServiceImpl implements Sys_UserService {

    @Resource
    private Sys_UserDao userDao;

    /**
     * 添加用户
     * @param sysUser 用户对象
     * @return
     */
    @Override
    @Transactional
//    @RequiresRoles("系统管理员")
    public Integer addUser(Sys_User sysUser) {
        Sys_User user1 = userDao.findUserByMobile(sysUser.getMobile());
        if(user1 != null ){
            throw new ServiceException("该账号已经存在");
        }
        log.info("开始插入用户数据");
        int user = userDao.addUser(sysUser);
        log.info("用户主键ID："+sysUser.getId());
        int RoleID = userDao.findRoleIdByRoleName(sysUser.getSysRole().getRoleName());
        int i1 = userDao.addUserIDAndRoleID(sysUser.getId(), RoleID);
        return null;
    }

    @Override
    public List<Sys_User> queryTeachers() {
        //查询所有老师信息
        return userDao.findTeachers();
    }

    /**
     * 修改教师信息
     * @param id 唯一标识
     * @param username 用户姓名
     * @param mobile 账号
     * @param status 状态
     * @param remark 教师信息
     */
    @Override
    public Integer updateTeacher(Integer id, String username, String mobile, Integer status, String remark) {
        return userDao.updateTeacher(id, username, mobile, status, remark);
    }

    @Override
    public int deleteTeacher(Integer id) {
        return userDao.deleteTeacher(id);
    }
}
