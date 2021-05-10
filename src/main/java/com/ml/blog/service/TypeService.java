package com.ml.blog.service;

import com.github.pagehelper.PageInfo;
import com.ml.blog.entity.Type;

import java.util.List;

/**
 * @author Mr.ml
 * @date 2021/1/16
 */
public interface TypeService {

    /**
     * 保存类型信息
     * @param type
     * @return
     */
    int saveType(Type type);

    /**
     * 根据类型id删除类型信息
     * @param typeId
     * @return
     */
    int removeType(Integer typeId);

    /**
     * 根据类型id更新类型信息
     * @param type
     * @return
     */
    int updateType(Type type);

    /**
     * 根据类型id得到类型信息
     * @param typeId
     * @return
     */
    Type getType(Integer typeId);

    /**
     * 根据类型名称得到类型信息
     * @param name
     * @return
     */
    Type getTypeByName(String name);

    /**
     * 查找所有类型信息
     * @return
     */
    List<Type> listTypes();

    /**
     * 查找所有类型信息，并封装为PageInfo对象
     * @return
     */
    PageInfo<Type> listTypes(Integer pageNum, Integer pageSize);

    /**
     * 查找顶部的size个类型信息
     * @param size
     * @return
     */
    PageInfo<Type> listTypesTop(Integer size);

}
