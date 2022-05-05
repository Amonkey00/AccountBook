package com.example.accountbook.vo.user;

import com.example.accountbook.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class UserListRespVo {
    List<User> userList;
    Integer pageSize;
    Integer totalSize;
}
