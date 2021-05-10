package com.ml.blog.service;

import com.github.pagehelper.PageInfo;
import com.ml.blog.entity.Tag;

import java.util.List;

/**
 * @author Mr.ml
 * @date 2021/1/16
 */
public interface TagService {

    /**
     * 保存标签信息
     * @param tag
     * @return
     */
    int saveTag(Tag tag);

    /**
     * 根据标签id删除标签信息
     * @param tagId
     * @return
     */
    int removeTag(Integer tagId);

    /**
     * 根据标签id更新标签信息
     * @param type
     * @return
     */
    int updateTag(Tag type);

    /**
     * 根据标签id得到标签信息
     * @param tagId
     * @return
     */
    Tag getTag(Integer tagId);

    /**
     * 根据标签名称得到标签信息
     * @param name
     * @return
     */
    Tag getTagByName(String name);

    /**
     * 查找所有标签信息
     * @return
     */
    List<Tag> listTags();

    /**
     * 查找所有标签信息，并封装为PageInfo对象
     * @return
     */
    PageInfo<Tag> listTags(Integer pageNum, Integer pageSize);

    /**
     * 查找顶部的size个标签信息
     * @param size
     * @return
     */
    PageInfo<Tag> listTagsTop(Integer size);

}
