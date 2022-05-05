package com.example.accountbook;

import com.example.accountbook.dao.UserMapper;
import com.example.accountbook.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class AccountBookApplicationTests {

    @Resource
    UserMapper userMapper;
    @Test
    void contextLoads() {
        User user = new User();
        user.setName("张明哲");
        user.setAccount("amonkey00");
        user.setPassword("123123123");
        user.setIntroduction("这是一个测试的用户");
        userMapper.insert(user);
    }

}
