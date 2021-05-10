package com.ml.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ml.blog.entity.Tag;
import com.ml.blog.exception.*;
import com.ml.blog.mapper.ArticleTagMapper;
import com.ml.blog.mapper.TagMapper;
import com.ml.blog.service.TagService;
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
public class TagServiceImpl implements TagService {

    @Resource
    private TagMapper tagMapper;
    @Resource
    private ArticleTagMapper articleTagMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int saveTag(Tag tag) {
        int res = tagMapper.insertTag(tag);
        if (res == -1) {
            throw new InsertException("标签插入失败");
        }
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int removeTag(Integer tagId) {
        int res = tagMapper.deleteTag(tagId);
        res |= articleTagMapper.deleteArticleTagByTagId(tagId);
        if (res == -1) {
            throw new DeleteException();
        }
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateTag(Tag tag) {
        Tag t = tagMapper.getTag(tag.getTagId());
        if (t == null) {
            throw new NotFoundException("不存在该标签");
        }
        BeanUtils.copyProperties(tag, t);
        int res = tagMapper.updateTag(t);
        if (res == -1) {
            throw new UpdateException("标签更新失败");
        }
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Tag getTag(Integer tagId) {
        Tag tag = tagMapper.getTag(tagId);
        if (tag == null) {
            throw new SelectException("标签查询失败");
        }
        return tag;
    }

    @Override
    public Tag getTagByName(String name) {
        Tag tag = tagMapper.getTagByName(name);
        if (tag == null) {
            throw new SelectException("标签查询失败");
        }
        return tag;
    }

    @Override
    public List<Tag> listTags() {
        List<Tag> tags = tagMapper.listTags();
        if (tags == null) {
            throw new SelectException("标签查询失败");
        }
        return tags;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PageInfo<Tag> listTags(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, "tag_id desc");
        List<Tag> tags = tagMapper.listTags();
        if (tags == null) {
            throw new SelectException("标签查询失败");
        }
        return new PageInfo<>(tags);
    }

    @Override
    public PageInfo<Tag> listTagsTop(Integer size) {
        //PageHelper.startPage(1, size, "articles.size() desc");
        List<Tag> tags = tagMapper.listTags();
        if (tags == null) {
            throw new SelectException("标签查询失败");
        }
        return new PageInfo<>(tags);
    }

}
