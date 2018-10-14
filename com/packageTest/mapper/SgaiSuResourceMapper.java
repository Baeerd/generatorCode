package com.packageTest.mapper;

import com.packageTest.entity.tableName;

import java.util.List;
import java.util.Map;

@Repository
public interface SgaiSuResourceMapper {

    public void insert(SgaiSuResource tableName);

    public void update(SgaiSuResource tableName);

    public void delete(Long sID);

    public List<SgaiSuResource> find(Map<String, Object> params);
}
