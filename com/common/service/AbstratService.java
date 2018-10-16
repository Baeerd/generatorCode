package com.common.service;

import java.util.List;
import java.util.Map;

public interface AbstratService<T> {

    public void insert(T entity);

    public void update(T entity);

    public void delete(T entity);

    public List<T> find(Map<String, Object> params);

}
