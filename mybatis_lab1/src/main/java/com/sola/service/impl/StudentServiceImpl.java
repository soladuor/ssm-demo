package com.sola.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sola.mapper.StudentMapper;
import com.sola.pojo.Student;
import com.sola.service.StudentService;
import com.sola.utils.CastUtil;
import com.sola.utils.MapperTools;

import java.util.Map;

public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService {

    @Override
    protected StudentMapper getEntityMapper() {
        /*
         servlet是单例类，只在第一次访问的时候会初始化一次，之后一直调用的都是同一个属性
        如果直接返回一个 StudentMapper 则会导致第一次访问结束后 SQLSession 关闭，
        进而导致第二次无法调用 Mapper (因为调用的是同一个 Mapper ，而这个 Mapper 的SQLSession却已经关闭了)
        调用返回值为对应的 Mapper 的方法则可以保证后续请求时获取的 Mapper 不会是第一次请求时 SQLSession 中的 Mapper
        */
        return MapperTools.getMapper(StudentMapper.class);
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
