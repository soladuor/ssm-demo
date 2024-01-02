package com.sola.service.impl;

import com.sola.mapper.BaseMapper;
import com.sola.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

@Transactional // 开启事务
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    protected abstract BaseMapper<T> getEntityMapper();

    public Integer insert(T t) {
        return getEntityMapper().insert(t);
    }

    public void delete(Object id) {
        getEntityMapper().delete(id);
    }

    public Integer update(T t) {
        return getEntityMapper().update(t);
    }

    public T getById(Object id) {
        return getEntityMapper().getById(id);
    }

}
