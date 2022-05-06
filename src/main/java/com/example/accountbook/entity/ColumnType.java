package com.example.accountbook.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("type_info")
public class ColumnType implements Serializable {
    @TableId(value="t_id", type= IdType.AUTO)
    private Integer tId;
    private String name;
    private String information;
}
