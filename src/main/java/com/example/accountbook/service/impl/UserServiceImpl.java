package com.example.accountbook.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.accountbook.dao.UserMapper;
import com.example.accountbook.entity.User;
import com.example.accountbook.model.PageResult;
import com.example.accountbook.service.UserService;
import com.example.accountbook.vo.user.UserListRespVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    /**
     * 添加用户
     */
    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    /**
     * 更新用户
     */
    @Override
    public int updateUser(User user) {
        return userMapper.updateById(user);
    }

    /**
     * 通过account 获取用户
     */
    @Override
    public User getUserByAccount(String account) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("account", account);
        User user = userMapper.selectOne(wrapper);
        return user;
    }

    @Override
    public User getUserById(Integer userId) {
        User user = userMapper.selectById(userId);
        return user;
    }

    /**
     * 获取用户列表
     */
    @Override
    public PageResult<User> getUserList(Integer groupId, Integer start, Integer size) {
        PageResult pageResult = new PageResult();
        List<User> userList = Collections.emptyList();
        // Query number
        Integer total = userMapper.countUserList(groupId);
        // Query data
        start = (start - 1) * size;
        userList = userMapper.queryUserList(groupId, start, size);
        pageResult = new PageResult(userList);

        pageResult.parsePage(total,size);

        return pageResult;
    }

    @Override
    public PageResult<User> searchUser(String keyword, Integer start, Integer size) {
        PageResult<User> result = new PageResult<>();

        // query like
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("account", "%"+keyword+"%");
        Page<User> userPage = userMapper.selectPage(new Page<>(start, size), wrapper);

        // build result
        result = new PageResult<>(userPage);
        return result;
    }
}
