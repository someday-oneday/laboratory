package com.codewhy.service;

import com.codewhy.common.web.ServiceException;
import com.codewhy.mapper.UserMapper;
import com.codewhy.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
@Transactional
public class UserService {

    @Resource
    private UserMapper userMapper;

    //添加教师用户
    public Integer addUser(User sysUser) {
        User user1 = userMapper.findUserByMobile(sysUser.getMobile());
        if(user1 != null ){
            throw new ServiceException("该账号已经存在");
        }
        log.info("开始插入用户数据");
        int user = userMapper.addUser(sysUser);
        log.info("用户主键ID："+sysUser.getId());
        int RoleID = userMapper.findRoleIdByRoleName(sysUser.getSysRole().getRoleName());
        int i1 = userMapper.addUserIDAndRoleID(sysUser.getId(), RoleID);
        return null;
    }

    //删除教师
    public int deleteTeacher(Integer id) {
        return userMapper.deleteTeacher(id);
    }

    //修改教师信息
    public Integer updateTeacher(Integer id, String username, String mobile, Integer status, String remark) {
        return userMapper.updateTeacher(id, username, mobile, status, remark);
    }

    //查询所有老师信息
    public List<User> queryTeachers() {
        return userMapper.findTeachers();
    }
}
