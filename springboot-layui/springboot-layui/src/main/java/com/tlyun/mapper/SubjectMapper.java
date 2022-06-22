package com.tlyun.mapper;

import com.tlyun.pojo.Subject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubjectMapper {
    List<Subject> getSubjectList(Subject subject);

    Subject getlistById(int picture);

    void insertlist(Subject subject);

    int updatesubejct(Subject subject);
}
