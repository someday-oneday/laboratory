package com.codewhy.service;

import com.codewhy.common.web.ServiceException;
import com.codewhy.mapper.LessonMapper;
import com.codewhy.mapper.LabMapper;
import com.codewhy.entity.Lab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LabService {
    @Autowired
    private LabMapper labMapper;

    @Autowired
    private LessonMapper lessonMapper;

    public int addLab(Lab lab) {
        int i = labMapper.queryLabCountBySerail(lab.getSerail());
        if(i>=1){
            throw new ServiceException("实验室编号已存在");
        }
        return  labMapper.addLab(lab);
    }

    public List<Lab> queryLabs(String lab) {
        return labMapper.queryLabs(lab);
    }

    @Transactional

    public Integer addSubscribe(Integer labID, Integer lesID) {
        lessonMapper.updateLabStatus(labID);
        lessonMapper.updateLesStatus(lesID);
        return lessonMapper.addSubscribe(labID, lesID);
    }
}
