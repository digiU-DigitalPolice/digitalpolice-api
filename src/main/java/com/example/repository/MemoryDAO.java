package com.example.repository;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Repository
public abstract class MemoryDAO<O,K extends Serializable> implements GenericDAO<O,K> {

    private Class<O> type;

    public MemoryDAO() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public K create(O t) {
        return null;
    }

    @Override
    public O read(K id) {
        return null;
    }

    @Override
    public void update(O t) {

    }

    @Override
    public void delete(O t) {

    }

    @Override
    public void delete(K id) {

    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Long count() {
        return null;
    }
}
