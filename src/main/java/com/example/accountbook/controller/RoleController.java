package com.example.accountbook.controller;

import com.example.accountbook.entity.Role;
import com.example.accountbook.service.RoleService;
import com.example.accountbook.utils.JsonResult;
import com.example.accountbook.utils.JwtUtil;
import com.example.accountbook.vo.role.RoleModifyReqVo;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final int OPS_ADD = 0;
    private final int OPS_UPDATE = 1;
    private final int OPS_DELETE = 2;

    @Autowired
    RoleService roleService;

    @GetMapping("/get")
    public JsonResult get(
            @RequestParam("userId") Integer userId,
            @RequestParam("groupId") Integer groupId
    ) {
        Role role = roleService.getRole(groupId, userId);
        if (role == null) {
            return new JsonResult(-1, "Role 不存在");
        }
        return new JsonResult(role);
    }

    @PostMapping("/add")
    public JsonResult add(
            @RequestBody RoleModifyReqVo reqVo
    ) {
        Role role = buildRole(reqVo);
        if (role == null) {
            return new JsonResult(-1,"role操作请求有误");
        }

        // 1. check role can do
        if (!checkModifyGroupRole(reqVo, OPS_ADD)){
            return new JsonResult(-1,"权限不足或已存在Role");
        }
        int flag = roleService.addRole(role);
        if (flag > 0) {
            return new JsonResult();
        }
        return new JsonResult(-1,"Role Insert添加失败");
    }

    @PostMapping("/update")
    public JsonResult update(
            @RequestBody RoleModifyReqVo reqVo
    ) {
        Role role = roleService.getRole(reqVo.getGroupId(),reqVo.getTargetId());
        if (role == null) {
            return new JsonResult(-1,"role操作请求有误");
        }

        // 1. check role can do
        if (!checkModifyGroupRole(reqVo, OPS_UPDATE)){
            return new JsonResult(-1,"权限不足或role不存在");
        }
        int flag = roleService.updateRole(role,reqVo.getStatus());
        if (flag > 0) {
            return new JsonResult();
        }
        return new JsonResult(-1,"Role update失败");
    }

    @PostMapping("/delete")
    public JsonResult delete(
            @RequestBody RoleModifyReqVo reqVo
    ) {
        Role role = roleService.getRole(reqVo.getGroupId(),reqVo.getTargetId());
        if (role == null) {
            return new JsonResult(-1,"role操作请求有误");
        }

        // 1. check role can do
        if (!checkModifyGroupRole(reqVo, OPS_DELETE)){
            return new JsonResult(-1,"权限不足或role不存在");
        }
        int flag = roleService.deleteRole(role);
        if (flag > 0) {
            return new JsonResult();
        }
        return new JsonResult(-1,"Role delete失败");
    }



    // ============== Utils ==================
    private boolean validate(RoleModifyReqVo reqVo) {
        if (reqVo.getOperatorId() == null) return false;
        if (reqVo.getTargetId() == null) return false;
        if (reqVo.getGroupId() == null) return false;
        if (reqVo.getStatus() == null) return false;
        return true;
    }

    private Role buildRole(RoleModifyReqVo reqVo) {
        if (!validate(reqVo)) return null;
        Role role = new Role();
        role.setGroupId(reqVo.getGroupId());
        role.setUserId(reqVo.getTargetId());
        role.setStatus(reqVo.getStatus());
        return role;
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


    private boolean checkModifyGroupRole(RoleModifyReqVo reqVo, Integer method) {
        // Query role
        List<Integer> userIds = Arrays.asList(reqVo.getOperatorId(), reqVo.getTargetId());
        Map<Integer, Integer> roleMap = roleService.getGroupRoleMap(userIds, reqVo.getGroupId());
        Integer operatorRoleCode = roleMap.get(reqVo.getOperatorId());
        Integer targetRoleCode = roleMap.get(reqVo.getTargetId());

        // Check function
        boolean result = false;
        // Method: add
        if (method.equals(0)) {
            // target role exists
            if (targetRoleCode != null) return false;
            // no privilege
            if (operatorRoleCode <= reqVo.getStatus()) return false;
            result = true;
        }
        // Method: update
        else if (method.equals(1)) {
            if (targetRoleCode == null) return false;
            if (operatorRoleCode <= reqVo.getStatus()) return false;
            result = true;
        }
        // Method: delete
        else if (method.equals(2)) {
            if (targetRoleCode == null) return false;
            if (operatorRoleCode <= targetRoleCode) return false;
            result = true;
        }
        return result;
    }
}
