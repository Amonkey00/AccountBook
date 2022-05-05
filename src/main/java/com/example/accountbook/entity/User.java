package com.example.accountbook.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user_info")
public class User {
    private int uId;
    private String name;
    private String account;
    private String password;
    private String email;
    private String phone;
    private Date createTime;
    private String status;
    private String introduction;
}
