package com.sola.service.impl;

import com.sola.mapper.SchoolClassMapper;
import com.sola.pojo.SchoolClass;
import com.sola.service.SchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolClassServiceImpl extends BaseServiceImpl<SchoolClass> implements SchoolClassService {

    @Autowired
    SchoolClassMapper schoolClassMapper;

    @Override
    protected SchoolClassMapper getEntityMapper() {
        return schoolClassMapper;
    }

}
