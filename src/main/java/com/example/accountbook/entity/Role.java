package com.example.accountbook.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("group_role")
public class Role {
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer groupId;
}
