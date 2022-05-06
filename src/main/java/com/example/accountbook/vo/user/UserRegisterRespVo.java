package com.example.accountbook.vo.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserRegisterRespVo implements Serializable {
    private String name;
    private String account;
    private String password;
    private String email;
    private String phone;
    private String introduction;
}
