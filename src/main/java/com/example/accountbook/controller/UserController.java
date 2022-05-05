package com.example.accountbook.controller;

import com.example.accountbook.entity.User;
import com.example.accountbook.service.UserService;
import com.example.accountbook.utils.JsonResult;
import com.example.accountbook.utils.JwtUtil;
import com.example.accountbook.vo.user.UserLoginRespVo;
import com.example.accountbook.vo.user.UserRegisterRespVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public JsonResult register(
            UserRegisterRespVo registerVo
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
            UserLoginRespVo loginRespVo
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
