package com.ml.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ml.blog.entity.Article;
import com.ml.blog.entity.Type;
import com.ml.blog.exception.*;
import com.ml.blog.mapper.ArticleMapper;
import com.ml.blog.mapper.TypeMapper;
import com.ml.blog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Mr.ml
 * @date 2021/1/16
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Resource
    private TypeMapper typeMapper;
    @Resource
    private ArticleMapper articleMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int saveType(Type type) {
        int res  = typeMapper.insertType(type);
        if (res == -1) {
            throw new InsertException("");
        }
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int removeType(Integer typeId) {
        int res = typeMapper.deleteType(typeId);
        List<Article> articles = articleMapper.listArticlesByTypeId(typeId);
        articles.forEach(article -> {
            article.setTypeId(0);
            articleMapper.updateArticle(article);
        });
        if (res == -1) {
            throw new DeleteException("");
        }
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateType(Type type) {
        Type t = typeMapper.getType(type.getTypeId());
        if (t == null) {
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type, t);
        int res = typeMapper.updateType(t);
        if (res == -1) {
            throw new UpdateException("");
        }
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Type getType(Integer id) {
        Type type = typeMapper.getType(id);
        if (type == null) {
            throw new SelectException("");
        }
        return type;
    }

    @Override
    public Type getTypeByName(String name) {
        Type type = typeMapper.getTypeByName(name);
        if (type == null) {
            throw new SelectException("");
        }
        return type;
    }

    @Override
    public List<Type> listTypes() {
        List<Type> types = typeMapper.listTypes();
        if (types == null) {
            throw new SelectException("");
        }
        return types;
    }

    @Override
    public PageInfo<Type> listTypes(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, "type_id desc");
        List<Type> types = typeMapper.listTypes();
        if (types == null) {
            throw new SelectException("");
        }
        return new PageInfo<>(types);
    }

    @Override
    public PageInfo<Type> listTypesTop(Integer size) {
        //PageHelper.startPage(1, size, "articles.size() desc");
        List<Type> types = typeMapper.listTypes();
        if (types == null) {
            throw new SelectException("");
        }
        return new PageInfo<>(types);
    }

}
