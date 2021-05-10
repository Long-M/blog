package com.ml.blog.service;

import com.github.pagehelper.PageInfo;
import com.ml.blog.entity.Visitor;

/**
 * @author Mr.ml
 * @date 2021/1/16
 */
public interface VisitorService {

    int saveVisitor(Visitor visitor);

    int removeVisitor(Integer visitorId);

    int updateVisitor(Visitor visitor);

    Visitor getVisitor(Integer visitorId);

    Visitor getVisitorByIP(String ip);

    PageInfo listVisitors(Integer pageNum, Integer pageSize);

}
