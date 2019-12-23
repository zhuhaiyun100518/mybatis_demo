package cn.ekgc.mybatisdemo.dao;

import cn.ekgc.mybatisdemo.entity.Student;

import java.util.List;

public interface StudentMapper {
    void save(Student student);
    List<Student> list();
}
