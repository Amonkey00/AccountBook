package com.example.accountbook.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.accountbook.dao.UserMapper;
import com.example.accountbook.entity.User;
import com.example.accountbook.model.PageResult;
import com.example.accountbook.service.UserService;
import com.example.accountbook.vo.user.MemberListVo;
import com.example.accountbook.vo.user.UserInfoVo;
import com.example.accountbook.vo.user.UserListRespVo;
import org.apache.commons.lang.StringUtils;
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

    @Override
    public int updateUser(UserInfoVo userInfoVo) {
        return userMapper.updateUser(userInfoVo);
    }

    /**
     * 通过account 获取用户
     */
    @Override
    public User getUserByAccount(String account) {
        if (account == null) return null;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("account", account);
        User user = userMapper.selectOne(wrapper);
        return user;
    }

    @Override
    public User getUserById(Integer userId) {
        if (userId == null) return null;
        User user = userMapper.selectById(userId);
        return user;
    }

    /**
     * 获取用户列表
     */
    @Override
    public PageResult<User> getUserList(Integer groupId, Integer role ,Integer start, Integer size) {

        PageResult pageResult = new PageResult();
        List<User> userList = Collections.emptyList();
        // Validate
        if (role != null && role.equals(-1)) role = null;
        // Query number
        Integer total = userMapper.countUserList(groupId,role);
        // Query data
        start = (start - 1) * size;
        userList = userMapper.queryUserList(groupId, role, start, size);
        pageResult = new PageResult(userList);
        pageResult.parsePage(total,size);

        return pageResult;
    }

    @Override
    public PageResult<UserInfoVo> searchUser(String keyword, Integer start, Integer size) {
        PageResult<UserInfoVo> result = new PageResult<>();

        // query like
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("account", "%"+keyword+"%");
        Page<User> userPage = userMapper.selectPage(new Page<>(start, size), wrapper);

        List<UserInfoVo> voList = new ArrayList<>();
        for (User user : userPage.getRecords()) {
            voList.add(new UserInfoVo(user));
        }
        result.setDataList(voList);
        result.setTotal((int) userPage.getTotal());
        result.setPageSize((int) userPage.getPages());

        // build result
        return result;
    }

    @Override
    public PageResult<MemberListVo> searchMember(Integer groupId, String keyword, Integer role, Integer start, Integer size) {
        PageResult<MemberListVo> result = new PageResult<>();
        List<MemberListVo> dataList = Collections.emptyList();

        if (StringUtils.isBlank(keyword)) keyword = null;
        if (role != null && role.equals(-1)) role = null;
        Integer total = userMapper.countMemberList(groupId, keyword, role);
        start = Math.min((start - 1) * size, 0);
        dataList = userMapper.queryMemberList(groupId, keyword, role, start, size);

        // queryRole

        result.setDataList(dataList);
        result.parsePage(total,size);

        return result;
    }

    @Override
    public Integer countTotal(Integer groupId) {
        // Query number
        Integer total = userMapper.countUserList(groupId,null);
        return total;
    }
}
