package com.example.accountbook.service;
import com.example.accountbook.entity.User;
import com.example.accountbook.model.PageResult;
import com.example.accountbook.vo.user.UserInfoVo;
import com.example.accountbook.vo.user.UserListRespVo;

import java.util.List;

public interface UserService {

    int addUser(User user);

    /**
     * 更新用户
     */
    int updateUser(User user);
    int updateUser(UserInfoVo userInfoVo);

    /**
     * 获取用户
     */
    User getUserByAccount(String account);

    User getUserById(Integer userId);

    PageResult<User> getUserList(Integer groupId, Integer start, Integer size);

    PageResult<UserInfoVo> searchUser(String keyword, Integer start, Integer size);

}
