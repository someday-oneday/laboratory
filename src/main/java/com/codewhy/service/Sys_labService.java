package com.codewhy.service;

import com.codewhy.pojo.Sys_lab;

import java.util.List;

public interface Sys_labService {
    int addLab(Sys_lab sys_lab);

    List<Sys_lab> queryLabs(String lab);

    Integer addSubscribe(Integer labID,Integer lesID);
}
