package com.codewhy.dao;

import com.codewhy.pojo.Sys_lab;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Sys_labDao {
    /**
     * 添加实验室模板
     * @param sys_lab
     * @return
     */
    int addLab(Sys_lab sys_lab);

    int queryLabCountBySerail(@Param("serail") String serail);

    List<Sys_lab> queryLabs(@Param("lab")String lab);
}
