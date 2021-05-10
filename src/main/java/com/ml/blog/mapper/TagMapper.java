package com.ml.blog.mapper;

import com.ml.blog.entity.Tag;

import java.util.List;

public interface TagMapper {
    int insertTag(Tag tag);

    int deleteTag(Integer tagId);

    int updateTag(Tag tag);

    Tag getTag(Integer tagId);

    Tag getTagByName(String tagName);

    List<Tag> listTags();
}