package com.codewhy.mapper;

import com.codewhy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 添加用户
     * @param sysUser 用户对象
     * @return
     */
    int addUser( User sysUser);

    /**
     * 根据角色名字查找角色ID
     * @param name 角色名字
     * @return
     */
    int findRoleIdByRoleName(@Param("name") String name);

    /**
     * 绑定用户与角色的关系
     * @param userID
     * @param roleID
     * @return
     */
    int addUserIDAndRoleID(@Param("userID")Integer userID,@Param("roleID")Integer roleID);

    /**
     * 根据电话号码查询用户
     * @param mobile
     * @return
     */
    User findUserByMobile(@Param("mobile") String mobile);

    /**
     * 查询教师信息
     * @return
     */
    List<User> findTeachers();

    /**
     * 修改教师信息
     * @param id 修改标识
     * @param username 用户名
     * @param mobile 账号
     * @param status 状态
     * @param remark 教师信息
     */
    int updateTeacher(@Param("id")Integer id,@Param("username") String username,@Param("mobile")String mobile,
                      @Param("status")Integer status,@Param("remark")String remark);

    /**
     * 删除教师
     * @param id 唯一标识
     * @return
     */
    int deleteTeacher(@Param("id")Integer id);
}
