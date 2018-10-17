package com.packageTest.service;

import com.packageTest.entity.SgaiSuResource;

import java.util.List;
import java.util.Map;

public interface SgaiSuResourceService {

    public void insert(SgaiSuResource sgaiSuResource);

    public void update(SgaiSuResource sgaiSuResource);

    public void delete(SgaiSuResource sgaiSuResource);

    public List<SgaiSuResource> find(Map<String, Object> params);

}
