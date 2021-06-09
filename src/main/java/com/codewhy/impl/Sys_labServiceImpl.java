package com.codewhy.impl;

import com.codewhy.common.web.ServiceException;
import com.codewhy.dao.Sys_LesDao;
import com.codewhy.dao.Sys_labDao;
import com.codewhy.pojo.Sys_lab;
import com.codewhy.service.Sys_labService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Sys_labServiceImpl implements Sys_labService {
    @Autowired
    private Sys_labDao sys_labDao;

    @Autowired
    private Sys_LesDao sys_lesDao;

    @Override
    public int addLab(Sys_lab sys_lab) {
        int i = sys_labDao.queryLabCountBySerail(sys_lab.getSerail());
        if(i>=1){
            throw new ServiceException("实验室编号已存在");
        }
        return  sys_labDao.addLab(sys_lab);
    }

    @Override
    public List<Sys_lab> queryLabs(String lab) {
        return sys_labDao.queryLabs(lab);
    }

    @Transactional
    @Override
    public Integer addSubscribe(Integer labID, Integer lesID) {
        sys_lesDao.updateLabStatus(labID);
        sys_lesDao.updateLesStatus(lesID);
        return sys_lesDao.addSubscribe(labID, lesID);
    }
}
