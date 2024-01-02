package com.sola.mapper;

public interface BaseMapper<T> {

    /**
     * 增加
     */
    Integer insert(T t);

    /**
     * 删除
     */
    void delete(Object id);

    /**
     * 修改
     */
    Integer update(T t);

    /**
     * 根据id查询
     */
    T getById(Object id);

}
