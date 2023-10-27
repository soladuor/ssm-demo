package com.sola.service;

import com.github.pagehelper.PageInfo;
import com.sola.pojo.Student;

import java.util.Map;

public interface StudentService extends BaseService<Student> {
    PageInfo<Student> findPage(Map<String, Object> filters);

}
