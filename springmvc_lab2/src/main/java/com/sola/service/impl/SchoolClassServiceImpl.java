package com.sola.service.impl;

import com.sola.mapper.SchoolClassMapper;
import com.sola.pojo.SchoolClass;
import com.sola.service.SchoolClassService;
import com.sola.utils.MapperTools;
import org.springframework.stereotype.Service;

@Service
public class SchoolClassServiceImpl extends BaseServiceImpl<SchoolClass> implements SchoolClassService {

    @Override
    protected SchoolClassMapper getEntityMapper() {
        return MapperTools.getMapper(SchoolClassMapper.class);
    }

}
