package com.common.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AbstratEntity {

    private Long sid;

    private String resId;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

}
