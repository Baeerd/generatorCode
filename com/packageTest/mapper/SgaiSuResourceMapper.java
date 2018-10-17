package com.packageTest.mapper;

import org.springframework.stereotype.Repository;

import com.packageTest.entity.SgaiSuResource;

import java.util.List;
import java.util.Map;

@Repository
public interface SgaiSuResourceMapper {

    public void insert(SgaiSuResource sgaiSuResource);

    public void update(SgaiSuResource sgaiSuResource);

    public void delete(SgaiSuResource sgaiSuResource);

    public List<SgaiSuResource> find(Map<String, Object> params);

}
