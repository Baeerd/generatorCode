package com.packageTest.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SgaiSuResource {

    private DECIMAL sid;

    private VARCHAR resId;

    private VARCHAR resName;

    private DECIMAL resType;

    private VARCHAR resUri;

    private DECIMAL resLevel;

    private DECIMAL parentSid;

    private VARCHAR createdBy;

    private DATE createdDt;

    private VARCHAR updatedBy;

    private DATE updatedDt;

    private DECIMAL version;

    private DECIMAL resSeq;

    private VARCHAR resParams;

    private VARCHAR visibility;

    public DECIMAL getSid() {
        return sid;
    }

    public void setSid(DECIMAL sid) {
        this.sid = sid;
    }

    public VARCHAR getResId() {
        return resId;
    }

    public void setResId(VARCHAR resId) {
        this.resId = resId;
    }

    public VARCHAR getResName() {
        return resName;
    }

    public void setResName(VARCHAR resName) {
        this.resName = resName;
    }

    public DECIMAL getResType() {
        return resType;
    }

    public void setResType(DECIMAL resType) {
        this.resType = resType;
    }

    public VARCHAR getResUri() {
        return resUri;
    }

    public void setResUri(VARCHAR resUri) {
        this.resUri = resUri;
    }

    public DECIMAL getResLevel() {
        return resLevel;
    }

    public void setResLevel(DECIMAL resLevel) {
        this.resLevel = resLevel;
    }

    public DECIMAL getParentSid() {
        return parentSid;
    }

    public void setParentSid(DECIMAL parentSid) {
        this.parentSid = parentSid;
    }

    public VARCHAR getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(VARCHAR createdBy) {
        this.createdBy = createdBy;
    }

    public DATE getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(DATE createdDt) {
        this.createdDt = createdDt;
    }

    public VARCHAR getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(VARCHAR updatedBy) {
        this.updatedBy = updatedBy;
    }

    public DATE getUpdatedDt() {
        return updatedDt;
    }

    public void setUpdatedDt(DATE updatedDt) {
        this.updatedDt = updatedDt;
    }

    public DECIMAL getVersion() {
        return version;
    }

    public void setVersion(DECIMAL version) {
        this.version = version;
    }

    public DECIMAL getResSeq() {
        return resSeq;
    }

    public void setResSeq(DECIMAL resSeq) {
        this.resSeq = resSeq;
    }

    public VARCHAR getResParams() {
        return resParams;
    }

    public void setResParams(VARCHAR resParams) {
        this.resParams = resParams;
    }

    public VARCHAR getVisibility() {
        return visibility;
    }

    public void setVisibility(VARCHAR visibility) {
        this.visibility = visibility;
    }

}
