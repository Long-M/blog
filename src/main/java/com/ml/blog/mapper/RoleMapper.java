package com.ml.blog.mapper;

import com.ml.blog.entity.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKey(Role record);
}