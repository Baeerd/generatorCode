package com.packageTest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packageTest.entity.SgaiSuResource;
import com.packageTest.mapper.SgaiSuResourceMapper;
import com.packageTest.service.SgaiSuResourceService;

import java.util.List;
import java.util.Map;

@Service
public class SgaiSuResourceServiceImpl implements SgaiSuResourceService {

    @AutoWired
    private SgaiSuResourceMapper sgaiSuResourceMapper;

    public void insert(SgaiSuResource sgaiSuResource) {
        sgaiSuResourceMapper.insert(sgaiSuResource);
    }

    public void update(SgaiSuResource sgaiSuResource) {
        sgaiSuResourceMapper.update(sgaiSuResource);
    }

    public void delete(SgaiSuResource sgaiSuResource) {
        sgaiSuResourceMapper.delete(sgaiSuResource);
    }

    public List<SgaiSuResource> find(Map<String, Object> params) {
        return sgaiSuResourceMapper.find(params);
    }

}
