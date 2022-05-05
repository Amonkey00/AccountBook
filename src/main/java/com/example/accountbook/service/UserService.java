package com.example.accountbook.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.accountbook.dao.UserMapper;
import com.example.accountbook.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    /**
     * 添加用户
     */
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    /**
     * 更新用户
     */
    public int updateUser(User user) {
        return userMapper.updateById(user);
    }

    /**
     * 通过account 获取用户
     */
    public User getUserByAccount(String account) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("account", account);
        User user = userMapper.selectOne(wrapper);
        return null;
    }

    /**
     * 获取用户列表
     */
    public List<User> queryUserList(Integer groupId, Integer start, Integer pageSize) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (groupId != -1) {
            wrapper.eq("groupId", groupId);
        }
        List<User> result = new ArrayList<>();
        if (start < 0) {
            result =  userMapper.selectList(wrapper);
        }
        if (pageSize > 0) {
            Page<User> userPage = userMapper.selectPage(new Page<>(start, pageSize), wrapper);
            result = userPage.getRecords();
        }
        return result;
    }


}
