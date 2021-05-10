package com.ml.blog.service;

import com.ml.blog.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author Mr.ml
 * @date 2021/1/16
 */
public interface UserService extends UserDetailsService {

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    int saveUser(User user);

    /**
     * 根据用户id删除用户信息
     * @param userId
     * @return
     */
    int removeUser(Integer userId);

    /**
     * 根据用户id更新用户信息
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 根据用户id查找用户信息
     * @param userId
     * @return
     */
    User getUser(Integer userId);

    /**
     * 根据作者名称查找用户信息
     * @param author
     * @return
     */
    User getUserByAuthor(String author);

    /**
     * 根据用户名查找用户信息
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 得到所有的用户信息
     * @return
     */
    List<User> listUsers();

}
