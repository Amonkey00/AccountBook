package com.example.accountbook.controller;

import com.example.accountbook.annotations.PassToken;
import com.example.accountbook.entity.User;
import com.example.accountbook.model.PageResult;
import com.example.accountbook.service.UserService;
import com.example.accountbook.service.impl.UserServiceImpl;
import com.example.accountbook.utils.JsonResult;
import com.example.accountbook.utils.JwtUtil;
import com.example.accountbook.vo.user.UserListRespVo;
import com.example.accountbook.vo.user.UserLoginRespVo;
import com.example.accountbook.vo.user.UserRegisterRespVo;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    @PassToken
    public JsonResult register(
            @RequestBody UserRegisterRespVo registerVo
    ) {
        String token = "";
        User user = buildUser(registerVo);
        // build error
        if (user == null) {
            return new JsonResult(-1,"注册信息有误");
        }

        // 1. check account available
        User existsUser = userService.getUserByAccount(user.getAccount());
        if (existsUser != null) {
            return new JsonResult(-1,"账号Account已被占用");
        }
        // 2. insert user
        try {
            userService.addUser(user);
            user = userService.getUserByAccount(user.getAccount());
            token = JwtUtil.buildJwt(user.getUId());
            return new JsonResult(token);
        }catch (Exception e) {
            return new JsonResult(-1, "USER INSERT添加失败");
        }
    }

    @PostMapping("/login")
    public JsonResult login(
            @RequestBody UserLoginRespVo loginRespVo
    ) {
        // 1. check exists
        User existsUser = userService.getUserByAccount(loginRespVo.getAccount());
        if (existsUser == null) {
            return new JsonResult(-1,"Account 不存在");
        }

        if (!existsUser.getPassword().equals(loginRespVo.getPassword())) {
            return new JsonResult(-1,"Account 账号或密码错误");
        }

        String token = "";
        token = JwtUtil.buildJwt(existsUser.getUId());
        return new JsonResult<String>(token);
    }

    @GetMapping("/get")
    public JsonResult getUser(
            @RequestParam("userId") Integer userId
    ) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return new JsonResult(-1,"User 不存在该userId账户");
        }
        return new JsonResult(user);
    }

    @GetMapping("/getList")
    public JsonResult getUserList(
            @RequestParam(value = "groupId",required = false) Integer groupId,
            @RequestParam(value = "start") Integer start,
            @RequestParam(value = "size") Integer size
    ){
        PageResult userList = userService.getUserList(groupId, start, size);
        return new JsonResult(userList);
    }

    /**
     * 模糊匹配 搜索account
     */
    @GetMapping("/searchUser")
    public JsonResult searchUser(
            @RequestParam(value = "keyword") String keyword,
            @RequestParam(value = "start") Integer start,
            @RequestParam(value = "size") Integer size
    ) {
        PageResult<User> result = userService.searchUser(keyword, start, size);
        return new JsonResult(result);
    }


    @PostMapping("/update")
    public JsonResult update(
            @RequestBody User user
    ) {
        int flag = userService.updateUser(user);
        if (flag > 0) {
            return new JsonResult();
        }
        return new JsonResult(-1,"User 更新失败");
    }

    @GetMapping("/freshToken")
    public JsonResult freshToken(
            @Param("token") String token
    ) {
        try {
            String newToken = JwtUtil.freshJwt(token);
            return new JsonResult<String>(newToken);
        }catch (Exception e) {
            return new JsonResult(-1,"Token fresh失败");
        }
    }



    // ===================== Utils =================
    /**
     * 映射 registerVo -> entity.user
     */
    private User buildUser(UserRegisterRespVo vo) {
        // validate
        if (StringUtils.isBlank(vo.getName()) ||
                StringUtils.isBlank(vo.getAccount()) ||
                StringUtils.isBlank(vo.getPassword())){
            return null;
        }

        // Build user
        User user = new User();
        BeanUtils.copyProperties(vo, user);
        return user;
    }
}
