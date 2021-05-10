package com.ml.blog.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Set;

/**
 * @author Mr.ml
 * @date 2021/4/6
 */
public class FastJSONUtils {

    public static String serialize(Object object) {
        return JSON.toJSONString(object);
    }

    public static <T> T deserialize(String JSONString, Class<T> clazz) {
        return JSON.parseObject(JSONString, clazz);
    }

    public static <T> Set<T> deserializeToSet(String JSONString, Class<T> clazz) {
        return JSON.parseObject(JSONString, new TypeReference<Set<T>>(clazz) {
        });
    }

}
