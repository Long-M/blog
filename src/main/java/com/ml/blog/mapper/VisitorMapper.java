package com.ml.blog.mapper;

import com.ml.blog.entity.Visitor;

import java.util.List;

public interface VisitorMapper {
    int insertVisitor(Visitor visitor);

    int deleteVisitor(Integer visitorId);

    int updateVisitor(Visitor visitor);

    Visitor getVisitor(Integer visitorId);

    Visitor getVisitorByIP(String ip);

    List<Visitor> listVisitors();
}