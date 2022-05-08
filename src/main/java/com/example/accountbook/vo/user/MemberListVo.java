package com.example.accountbook.vo.user;

import lombok.Data;

@Data
public class MemberListVo {
    private Integer uId;
    private String name;
    private String account;
    private String email;
    private String phone;
    private String roleStatus;
    private String introduction;
}
