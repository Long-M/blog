package com.ml.blog.service.impl;

import com.ml.blog.entity.User;
import com.ml.blog.enums.ResultCodeEnum;
import com.ml.blog.exception.DeleteException;
import com.ml.blog.exception.InsertException;
import com.ml.blog.exception.SelectException;
import com.ml.blog.exception.UpdateException;
import com.ml.blog.mapper.UserMapper;
import com.ml.blog.service.UserService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mr.ml
 * @date 2021/1/16
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int saveUser(User user) {
        user.setCreateTime(new Date());
        //user.setRole(new Role(2, "user"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        int res = userMapper.insertUser(user);
        if (res == -1) {
            throw new InsertException(ResultCodeEnum.INSERT_FAIL, "用户新增失败");
        }
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int removeUser(Integer userId) {
        int res = userMapper.deleteUser(userId);
        if (res == -1) {
            throw new DeleteException(ResultCodeEnum.DELETE_FAIL, "用户删除失败");
        }
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateUser(User user) {
        user.setRecentLoginTime(new Date());
        int res = userMapper.updateUser(user);
        if (res == -1) {
            throw new UpdateException(ResultCodeEnum.UPDATE_FAIL, "用户更新失败");
        }
        return res;
    }

    @Override
    public User getUser(Integer userId) {
        User user = userMapper.getUser(userId);
        if (user == null) {
            throw new SelectException(ResultCodeEnum.SELECT_FAIL, "用户查询失败");
        }
        return user;
    }

    @Override
    public User getUserByAuthor(String author) {
        User user = userMapper.getUserByUsername(author);
        if (user == null) {
            throw new SelectException(ResultCodeEnum.SELECT_FAIL, "用户查询失败");
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userMapper.getUserByUsername(username);
        if (user == null) {
            throw new SelectException(ResultCodeEnum.SELECT_FAIL, "用户查询失败");
        }
        return user;
    }

    @Override
    public List<User> listUsers() {
        List<User> users = userMapper.listUsers();
        if (users == null) {
            throw new SelectException(ResultCodeEnum.SELECT_FAIL, "用户查询失败");
        }
        return users;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 用户基础数据加载
        User userDetails = userMapper.getUserByUsername(s);
        if (userDetails == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 用户的角色列表
        List<String> roleNames = userMapper.getRoleByUsername(s);
        // 根据角色列表加载当前用户具有的权限
        List<String> authorities = userMapper.getAuthorityByRoleNames(roleNames);
        roleNames = roleNames.stream()
                .map(rn -> "ROLE_" + rn)
                .collect(Collectors.toList());
        authorities.addAll(roleNames);
        userDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(
                String.join(",", authorities)
        ));
        return userDetails;
    }

}
