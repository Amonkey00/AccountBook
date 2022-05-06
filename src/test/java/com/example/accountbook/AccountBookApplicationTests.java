package com.example.accountbook;

import com.alibaba.fastjson.JSON;
import com.example.accountbook.dao.UserMapper;
import com.example.accountbook.entity.Group;
import com.example.accountbook.entity.User;
import com.example.accountbook.service.GroupService;
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
    @Resource
    GroupService groupService;
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
        Group group = groupService.getGroupById(6);
        System.out.println(JSON.toJSON(group));
    }




}
