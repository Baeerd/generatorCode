package com.common.mapper;

import java.util.List;
import java.util.Map;

public interface AbstratMapper<T> {

    public void insert(T entity);

    public void update(T entity);

    public void delete(T entity);

    public List<T> find(Map<String, Object> params);

}
