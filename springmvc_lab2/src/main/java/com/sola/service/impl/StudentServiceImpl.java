package com.sola.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sola.mapper.StudentMapper;
import com.sola.pojo.Student;
import com.sola.service.StudentService;
import com.sola.utils.CastUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    protected StudentMapper getEntityMapper() {
        return studentMapper;
    }

    @Override
    public PageInfo<Student> findPage(Map<String, Object> filters) {
        // 当前页数
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        // 每页显示的记录条数
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 10);

        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(getEntityMapper().findPage(filters), 10);
    }

}
