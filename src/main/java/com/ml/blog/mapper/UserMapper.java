package com.ml.blog.mapper;

import com.ml.blog.entity.User;
import com.ml.blog.vo.ResultVO;

import java.util.List;

public interface UserMapper {
    int insertUser(User user);

    int deleteUser(Integer userId);

    int updateUser(User user);

    User getUser(Integer userId);

    User getUserByUsername(String username);

    List<String> getRoleByUsername(String username);

    List<String> getAuthorityByRoleNames(List<String> roleNames);

    List<User> listUsers();
}