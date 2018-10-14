package com.test.controller;

import com.test.entity.TestEntity;
import com.test.service.TestEntityService;

import java.util.List;
import java.util.Map;

//@Controller()
//@RequestMapping("/testEntity")
public class TestEntityController {

    //@AutoWired
    private TestEntityService testEntityService;

    //@RequestMapping("/insert")
    public void insert(TestEntity testEntity) {
        testEntityService.insert(testEntity);
    }

    //@RequestMapping("/update")
    public void update(TestEntity testEntity) {
        testEntityService.update(testEntity);
    }

    //@RequestMapping("/delete")
    public void delete(Long sid) {
        testEntityService.delete(sid);
    }

    //@RequestMapping("/find")
    public List<TestEntity> find(Map<String, Object> params) {
        return testEntityService.find(params);
    }
}
