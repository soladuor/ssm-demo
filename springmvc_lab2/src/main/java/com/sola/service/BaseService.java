package com.sola.service;

public interface BaseService<T> {
    Integer insert(T t);

    void delete(Object id);

    Integer update(T t);

    T getById(Object id);

}
