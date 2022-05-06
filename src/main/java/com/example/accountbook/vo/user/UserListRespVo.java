package com.example.accountbook.vo.user;

import com.example.accountbook.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserListRespVo implements Serializable {
    List<User> userList;
    Integer pageSize;
    Integer totalSize;
}
