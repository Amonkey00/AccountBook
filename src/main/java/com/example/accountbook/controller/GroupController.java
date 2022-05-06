package com.example.accountbook.controller;

import com.example.accountbook.entity.Group;
import com.example.accountbook.entity.Role;
import com.example.accountbook.enums.RoleStatusEnum;
import com.example.accountbook.model.PageResult;
import com.example.accountbook.service.GroupService;
import com.example.accountbook.service.RoleService;
import com.example.accountbook.utils.JsonResult;
import com.example.accountbook.utils.JwtUtil;
import com.example.accountbook.vo.group.GroupCreateReqVo;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupService groupService;
    @Autowired
    RoleService roleService;

    /**
     * 创建group
     */
    @PostMapping("/create")
    public JsonResult create(
            @RequestBody GroupCreateReqVo reqVo
    ) {
        Group group = buildGroup(reqVo);
        if (group == null) {
            return new JsonResult(-1,"Group信息有误");
        }

        // 1. check exists
        Group existsGroup = groupService.getGroupByName(reqVo.getGroupName());
        if (existsGroup != null) {
            return new JsonResult(-1,"Group name已经存在");
        }
        // 2. insert
        int flag = groupService.addGroup(group);
        if (flag <= 0) {
            return new JsonResult(-1, "group Insert失败");
        }
        // 3. insert role
        try {
            group = groupService.getGroupByName(reqVo.getGroupName());
            Role role = new Role(reqVo.getCreatorId(), group.getGId(), RoleStatusEnum.CREATOR.getCode());
            roleService.addRole(role);
            return new JsonResult();
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(-1,"组权限设置失败");
        }
    }

    /**
     * 更新group
     */
    @PostMapping("/update")
    public JsonResult update(
            HttpServletRequest request,
            @RequestBody Group group
    ) {
        String token = request.getHeader("token");
        Integer userId = parseToken2Id(token);
        if (!checkGroupRole(userId,group.getGId(),RoleStatusEnum.ADMIN.getCode())) {
            return new JsonResult(-1,"update失败。没有该权限");
        }
        int flag = groupService.updateGroup(group);
        if (flag > 0) {
            return new JsonResult();
        }
        return new JsonResult(-1,"Group 更新失败");
    }


    /**
     * 查询group
     */
    @GetMapping("/get")
    public JsonResult get(

            @RequestParam Integer groupId
    ) {
        Group group = groupService.getGroupById(groupId);
        if (group == null) {
            return new JsonResult(-1, "不存在该group");
        }
        return new JsonResult(group);
    }

    @GetMapping("/getList")
    public JsonResult getList(
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam("start") Integer start,
            @RequestParam("size") Integer size
    ) {
        PageResult<Group> groupList = groupService.getGroupList(userId, start, size);
        return new JsonResult(groupList);
    }


    // ================ Utils =================
    private Group buildGroup(GroupCreateReqVo reqVo) {
        if (StringUtils.isBlank(reqVo.getGroupName()) ||
                StringUtils.isBlank(reqVo.getCreatorName()) ||
                reqVo.getCreatorId() == null
        ) {
            return null;
        }
        Group group = new Group();
        BeanUtils.copyProperties(reqVo,group);
        return group;
    }

    private Integer parseToken2Id(String token) {
        Integer userId = -1;
        try {
            Claims claims = JwtUtil.parseJwt(token);
            userId = (Integer) claims.get("userId");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return userId;
    }

    private boolean checkGroupRole(Integer userId, Integer groupId, Integer roleCode) {
        if (groupId == null || userId == null) return false;
        Role role = roleService.getRole(groupId, userId);
        // Query role and check code
        if (role == null || role.getStatus() < roleCode) return false;
        return true;
    }
}
