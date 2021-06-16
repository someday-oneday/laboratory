package com.codewhy.mapper;

import com.codewhy.entity.Lab;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LabMapper {
    /**
     * 添加实验室模板
     * @param lab
     * @return
     */
    int addLab(Lab lab);

    int queryLabCountBySerail(@Param("serail") String serail);

    List<Lab> queryLabs(@Param("lab")String lab);
}
