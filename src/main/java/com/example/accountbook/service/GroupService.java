package com.example.accountbook.service;

import com.example.accountbook.entity.Group;
import com.example.accountbook.model.PageResult;

public interface GroupService {

    /**
     * 添加 group
     */
    int addGroup(Group group);

    /**
     * 修改 group
     */
    int updateGroup(Group group);


    /**
     * 获取group 不同方式
     */
    Group getGroupById(Integer groupId);
    Group getGroupByName(String name);
    PageResult<Group> getGroupList(Integer userId, Integer start, Integer size);




}
