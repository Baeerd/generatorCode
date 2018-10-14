package com.test.service;

import com.test.entity.TestEntity;

import java.util.List;
import java.util.Map;

public interface TestEntityService {

    public void insert(TestEntity testEntity);

    public void update(TestEntity testEntity);

    public void delete(Long sid);

    public List<TestEntity> find(Map<String, Object> params);
}
