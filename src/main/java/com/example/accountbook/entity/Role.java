package com.example.accountbook.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("group_role")
public class Role {
    private int id;
    private int userId;
    private int groupId;
}
