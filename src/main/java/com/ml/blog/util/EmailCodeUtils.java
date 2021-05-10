package com.ml.blog.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Mr.ml
 * @date 2021/1/16
 */
public class EmailCodeUtils {

    /**
     * 生成随机验证码
     * @return
     */
    public static String achieveCode() {
        String[] beforeShuffle= new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(3, 9);
        System.out.print(result);
        return result;
    }

}
