package com.test.mapper;

import com.test.entity.TestEntity;

import java.util.List;
import java.util.Map;

//@Repository
public interface TestEntityMapper {

    public void insert(TestEntity testEntity);

    public void update(TestEntity testEntity);

    public void delete(Long sid);

    public List<TestEntity> find(Map<String, Object> params);
}
