package com.example.accountbook.service;

import com.example.accountbook.entity.Role;
import com.example.accountbook.enums.RoleStatusEnum;

import java.util.List;
import java.util.Map;

public interface RoleService {

    public Role getRole(Integer groupId, Integer userId);
    /**
     * 增删改人员权限
     */
    public int addRole(Role role);
    public int deleteRole(Role role);
    public int updateRole(Role role, RoleStatusEnum roleEnum);

    /**
     * 获取权限
     */
    Integer getGroupRoleCode(Integer userId, Integer groupId);
    Map<Integer,Integer> getGroupRoleMap(List<Integer> userIds, Integer groupId);
}
