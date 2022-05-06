package com.example.accountbook.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@TableName("group_role")
@NoArgsConstructor
public class Role implements Serializable {
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;

    private Integer userId;
    private Integer groupId;
    private Integer status;

    public Role(Integer userId, Integer groupId, Integer status) {
        this.userId = userId;
        this.groupId = groupId;
        this.status = status;
    }
}
