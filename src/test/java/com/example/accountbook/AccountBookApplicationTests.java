package com.example.accountbook;

import com.alibaba.fastjson.JSON;
import com.example.accountbook.dao.UserMapper;
import com.example.accountbook.entity.User;
import com.example.accountbook.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class AccountBookApplicationTests {

    @Resource
    UserMapper userMapper;
    @Resource
    UserServiceImpl userService;
    @Test
    void contextLoads() {
        User user = new User();
        user.setName("张明哲2");
        user.setAccount("amonkey00");
        user.setPassword("123123123");
        user.setIntroduction("这是一个测试的用户");
        userMapper.insert(user);
    }

    @Test
    void test1() {
        User user = userService.getUserByAccount("amonkey00");
        System.out.println(JSON.toJSON(user));
    }




}
