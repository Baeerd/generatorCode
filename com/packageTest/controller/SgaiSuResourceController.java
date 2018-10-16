package com.packageTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import com.packageTest.entity.SgaiSuResource;
import com.packageTest.service.SgaiSuResourceService;

import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
@RequestMapping("/sgaiSuResource")
public class SgaiSuResourceController {

    @AutoWired
    private SgaiSuResourceService sgaiSuResourceService;

    @RequestMapping("/insert")
    public void insert(SgaiSuResource sgaiSuResource) {
        sgaiSuResourceService.insert(sgaiSuResource);
    }

    @RequestMapping("/update")
    public void update(SgaiSuResource sgaiSuResource) {
        sgaiSuResourceService.update(sgaiSuResource);
    }

    @RequestMapping("/delete")
    public void delete(SgaiSuResource sgaiSuResource) {
        sgaiSuResourceService.delete(sgaiSuResource);
    }

    @RequestMapping("/find")
    public List<SgaiSuResource> find(Map<String, Object> params) {
        return sgaiSuResourceService.find(params);
    }

}
