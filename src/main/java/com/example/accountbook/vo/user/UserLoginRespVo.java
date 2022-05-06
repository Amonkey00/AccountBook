package com.example.accountbook.vo.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginRespVo implements Serializable {
    public String account;
    public String password;
}
