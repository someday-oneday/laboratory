package com.codewhy.mapper;

import com.codewhy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    //添加用户
    int addUser( User sysUser);

    //根据角色名字查找角色ID
    int findRoleIdByRoleName(@Param("name") String name);

    //绑定用户与角色的关系
    int addUserIDAndRoleID(@Param("userID")Integer userID,@Param("roleID")Integer roleID);

    //根据电话号码查询用户
    User findUserByMobile(@Param("mobile") String mobile);

    //查询所有教师信息
    List<User> findTeachers();

    //修改教师信息
    int updateTeacher(@Param("id")Integer id,@Param("username") String username,@Param("mobile")String mobile,
                      @Param("status")Integer status,@Param("remark")String remark);

    //修改教师信息
    int deleteTeacher(@Param("id")Integer id);
}
