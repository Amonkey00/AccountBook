package com.example.accountbook.vo.user;

import com.example.accountbook.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class UserInfoVo {
    private Integer uId;
    private String name;
    private String account;
    private String email;
    private String phone;
    private String status;
    private String introduction;

    public UserInfoVo(User user){
        this.uId = user.getUId();
        this.name = user.getName();
        this.account = user.getAccount();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.status = user.getStatus();
        this.introduction = user.getIntroduction();
    }
}
