package com.example.accountbook.service;

import com.example.accountbook.entity.Role;
import com.example.accountbook.enums.RoleStatusEnum;

import java.util.List;
import java.util.Map;

public interface RoleService {

    Role getRole(Integer groupId, Integer userId);
    /**
     * 增删改人员权限
     */
    int addRole(Role role);
    int deleteRole(Role role);
    int updateRole(Role role, Integer statusCode);

    /**
     * 获取权限
     */
    Integer getGroupRoleCode(Integer userId, Integer groupId);
    Map<Integer,Integer> getGroupRoleMap(List<Integer> userIds, Integer groupId);
}
