package com.common.service;

import java.util.List;
import java.util.Map;

public interface AbstratServiceImpl<T> extends AbstratService<T> {

    protected Class<T> entityClass;

    protected String entityClassName;

    private ApplicationContext applicationContext;

    private AbstratMapper abstratMapper;

    public AbstratServiceImpl() {
        try {
            Object genericClz = getClass().getGenericSuperclass();
            if (genericClz instanceof ParameterizedType) {
                entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0];
                entityClassName = entityClass.getSimpleName();
            }
            abstratMapper = applicationContext.getBean(beanId);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public void insert(T entity) {

    }

    public void update(T entity) {

    }

    public void delete(T entity) {

    }

    public List<T> find(Map<String, Object> params) {

    }

}
