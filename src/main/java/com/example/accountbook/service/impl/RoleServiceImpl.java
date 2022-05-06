package com.example.accountbook.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.accountbook.dao.RoleMapper;
import com.example.accountbook.entity.Role;
import com.example.accountbook.enums.RoleStatusEnum;
import com.example.accountbook.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleMapper roleMapper;

    @Override
    public Role getRole(Integer groupId, Integer userId) {
        if (groupId == null || userId == null) return null;
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("group_id", groupId);
        wrapper.eq("user_id", userId);

        return roleMapper.selectOne(wrapper);
    }

    @Override
    public int addRole(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    public int deleteRole(Role role) {
        return roleMapper.deleteById(role);
    }

    @Override
    public int updateRole(Role role, Integer statusCode) {
        role.setStatus(statusCode);
        return roleMapper.updateById(role);
    }

    @Override
    public Integer getGroupRoleCode(Integer userId, Integer groupId) {
        Role role = getRole(userId, groupId);
        if (role == null) return -1;
        return role.getStatus();
    }

    @Override
    public Map<Integer,Integer> getGroupRoleMap(List<Integer> userIds, Integer groupId) {
        if (CollectionUtils.isEmpty(userIds) || groupId == null) return new HashMap<>();

        HashMap<Integer, Integer> resultMap = new HashMap<>();
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("group_id", groupId)
                .in("user_id", userIds);
        List<Role> roles = roleMapper.selectList(wrapper);
        for (Role role : roles) {
            resultMap.put(role.getUserId(), role.getStatus());
        }
        return resultMap;
    }
}
