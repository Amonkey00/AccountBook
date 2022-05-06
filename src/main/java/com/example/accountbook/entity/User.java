package com.example.accountbook.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("user_info")
public class User implements Serializable {
    @TableId(value="u_id", type= IdType.AUTO)
    private Integer uId;
    private String name;
    private String account;
    private String password;
    private String email;
    private String phone;
    private Date createTime;
    private String status;
    private String introduction;
}
