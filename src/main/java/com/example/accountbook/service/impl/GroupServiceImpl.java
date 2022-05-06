package com.example.accountbook.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.accountbook.dao.GroupMapper;
import com.example.accountbook.dao.RoleMapper;
import com.example.accountbook.entity.Group;
import com.example.accountbook.entity.Role;
import com.example.accountbook.model.PageResult;
import com.example.accountbook.service.GroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    GroupMapper groupMapper;

    @Override
    public int addGroup(Group group) {
        return groupMapper.insert(group);
    }

    @Override
    public int updateGroup(Group group) {
        return groupMapper.updateById(group);
    }

    @Override
    public Group getGroupById(Integer groupId) {
        if (groupId == null) return null;
        return groupMapper.selectById(groupId);
    }

    @Override
    public Group getGroupByName(String name) {
        if (name == null) return null;
        QueryWrapper<Group> wrapper = new QueryWrapper<>();
        wrapper.eq("group_name",name);
        Group group = groupMapper.selectOne(wrapper);
        return group;
    }

    @Override
    public PageResult<Group> getGroupList(Integer userId, Integer start, Integer size) {
        PageResult<Group> result = new PageResult<>();

        // Query total
        Integer total = groupMapper.countGroupList(userId);
        // Query data
        start = Math.max((start - 1) * size, 0);
        List<Group> groupList = groupMapper.queryGroupList(userId, start, size);
        // Parse pageNum
        result.setDataList(groupList);
        result.parsePage(total,size);

        return result;
    }

}
