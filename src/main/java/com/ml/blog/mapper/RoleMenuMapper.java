package com.ml.blog.mapper;

import com.ml.blog.entity.RoleMenu;

public interface RoleMenuMapper {
    int deleteByPrimaryKey(Integer roleMenuId);

    int insert(RoleMenu record);

    RoleMenu selectByPrimaryKey(Integer roleMenuId);

    int updateByPrimaryKey(RoleMenu record);
}