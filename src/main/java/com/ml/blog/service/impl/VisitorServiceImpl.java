package com.ml.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ml.blog.entity.Visitor;
import com.ml.blog.enums.ResultCodeEnum;
import com.ml.blog.exception.DeleteException;
import com.ml.blog.exception.InsertException;
import com.ml.blog.exception.SelectException;
import com.ml.blog.exception.UpdateException;
import com.ml.blog.mapper.VisitorMapper;
import com.ml.blog.service.VisitorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Mr.ml
 * @date 2021/1/16
 */
@Service
public class VisitorServiceImpl implements VisitorService {

    @Resource
    private VisitorMapper visitorMapper;

    @Override
    public int saveVisitor(Visitor visitor) {
        int res = visitorMapper.insertVisitor(visitor);
        if (res == -1) {
            throw new InsertException(ResultCodeEnum.INSERT_FAIL, "访客插入失败");
        }
        return res;
    }

    @Override
    public int removeVisitor(Integer visitorId) {
        int res = visitorMapper.deleteVisitor(visitorId);
        if (res == -1) {
            throw new DeleteException(ResultCodeEnum.DELETE_FAIL, "访客删除失败");
        }
        return res;
    }

    @Override
    public int updateVisitor(Visitor visitor) {
        int res = visitorMapper.updateVisitor(visitor);
        if (res == -1) {
            throw new UpdateException(ResultCodeEnum.UPDATE_FAIL, "访客更新失败");
        }
        return res;
    }

    @Override
    public Visitor getVisitor(Integer visitorId) {
        Visitor visitor = visitorMapper.getVisitor(visitorId);
        if (visitor == null) {
            throw new SelectException(ResultCodeEnum.SELECT_FAIL, "访客查询失败");
        }
        return visitor;
    }

    @Override
    public Visitor getVisitorByIP(String ip) {
        Visitor visitor = visitorMapper.getVisitorByIP(ip);
//        if (visitor == null) {
//            Visitor v = new Visitor();
//            v.setIpAddress(servletRequest.getRemoteAddr());
//            v.setRecentVisitTime(new Date());
//            v.setCount(1);
//            visitorService.saveVisitor(v);
//        } else {
//            visitor.setCount(visitor.getCount() + 1);
//            visitorService.updateVisitor(visitor);
//        }
        if (visitor == null) {
            throw new SelectException(ResultCodeEnum.SELECT_FAIL, "访客查询失败");
        }
        return visitor;
    }

    @Override
    public PageInfo listVisitors(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, "time desc");
        List<Visitor> visitors = visitorMapper.listVisitors();
        if (visitors == null) {
            throw new SelectException(ResultCodeEnum.SELECT_FAIL, "访客查询失败");
        }
        return new PageInfo<>(visitors);
    }

}
