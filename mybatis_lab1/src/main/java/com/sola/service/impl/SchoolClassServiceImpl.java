package com.sola.service.impl;

import com.sola.mapper.SchoolClassMapper;
import com.sola.pojo.SchoolClass;
import com.sola.service.SchoolClassService;
import com.sola.utils.MapperTools;

public class SchoolClassServiceImpl extends BaseServiceImpl<SchoolClass> implements SchoolClassService {

    @Override
    protected SchoolClassMapper getEntityMapper() {
        return MapperTools.getMapper(SchoolClassMapper.class);
    }

}
