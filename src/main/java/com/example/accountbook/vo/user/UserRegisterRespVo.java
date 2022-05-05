package com.example.accountbook.vo.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserRegisterRespVo {
    private String name;
    private String account;
    private String password;
    private String email;
    private String phone;
    private String introduction;
}
