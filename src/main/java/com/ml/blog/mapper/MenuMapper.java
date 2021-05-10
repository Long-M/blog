package com.ml.blog.mapper;

import com.ml.blog.entity.Menu;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(Menu record);

    Menu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKey(Menu record);
}