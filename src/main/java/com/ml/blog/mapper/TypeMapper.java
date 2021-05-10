package com.ml.blog.mapper;

import com.ml.blog.entity.Type;

import java.util.List;

public interface TypeMapper {
    int insertType(Type type);

    int deleteType(Integer typeId);

    int updateType(Type type);

    Type getType(Integer typeId);

    Type getTypeByName(String typeName);

    List<Type> listTypes();
}