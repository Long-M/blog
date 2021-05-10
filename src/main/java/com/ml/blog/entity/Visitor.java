package com.ml.blog.entity;

import java.util.Date;

public class Visitor {

    private Integer visitorId;

    private String ipAddress;

    private Date recentVisitTime;

    private Integer count;

    public Integer getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Integer visitorId) {
        this.visitorId = visitorId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getRecentVisitTime() {
        return recentVisitTime;
    }

    public void setRecentVisitTime(Date recentVisitTime) {
        this.recentVisitTime = recentVisitTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Visitor() {
    }

    public Visitor(Integer visitorId, String ipAddress, Date recentVisitTime, Integer count) {
        this.visitorId = visitorId;
        this.ipAddress = ipAddress;
        this.recentVisitTime = recentVisitTime;
        this.count = count;
    }

}