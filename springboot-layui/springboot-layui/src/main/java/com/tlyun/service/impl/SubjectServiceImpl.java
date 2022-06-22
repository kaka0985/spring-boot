package com.tlyun.service.impl;

import com.tlyun.mapper.SubjectMapper;
import com.tlyun.pojo.Subject;
import com.tlyun.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements ISubjectService {
    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public List<Subject> getSubjectList(Subject subject) {
        return subjectMapper.getSubjectList(subject);
    }
}
