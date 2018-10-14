package com.test.service.impl;

import com.test.entity.TestEntity;
import com.test.mapper.TestEntityMapper;
import com.test.service.TestEntityService;

import java.util.List;
import java.util.Map;

//@Service()
public class TestEntityServiceImpl implements TestEntityService {

    //@AutoWired
    private TestEntityMapper testEntityMapper;

    public void insert(TestEntity testEntity) {
        testEntityMapper.insert(testEntity);
    }

    public void update(TestEntity testEntity) {
        testEntityMapper.update(testEntity);
    }

    public void delete(Long sid) {
        testEntityMapper.delete(sid);
    }

    public List<TestEntity> find(Map<String, Object> params) {
        return testEntityMapper.find(params);
    }
}
