package com.lv.oa.dao;

import com.lv.oa.dto.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper {
    void addStudent(Student student);
}
