package com.packageTest.entity;

import com.common.entity.AbstratEntity;

import java.math.BigDecimal;
import java.util.Date;

public class SgaiSuResource extends AbstratEntity{

    private String resName;

    private Long resType;

    private String resUri;

    private Long resLevel;

    private Long parentSid;

    private String createdBy;

    private Date createdDt;

    private String updatedBy;

    private Date updatedDt;

    private Long version;

    private Long resSeq;

    private String resParams;

    private String visibility;

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public Long getResType() {
        return resType;
    }

    public void setResType(Long resType) {
        this.resType = resType;
    }

    public String getResUri() {
        return resUri;
    }

    public void setResUri(String resUri) {
        this.resUri = resUri;
    }

    public Long getResLevel() {
        return resLevel;
    }

    public void setResLevel(Long resLevel) {
        this.resLevel = resLevel;
    }

    public Long getParentSid() {
        return parentSid;
    }

    public void setParentSid(Long parentSid) {
        this.parentSid = parentSid;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDt() {
        return updatedDt;
    }

    public void setUpdatedDt(Date updatedDt) {
        this.updatedDt = updatedDt;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getResSeq() {
        return resSeq;
    }

    public void setResSeq(Long resSeq) {
        this.resSeq = resSeq;
    }

    public String getResParams() {
        return resParams;
    }

    public void setResParams(String resParams) {
        this.resParams = resParams;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

}
