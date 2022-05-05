package com.example.accountbook.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("type_info")
public class ColumnType {
    @TableId(value="t_id", type= IdType.AUTO)
    private Integer tId;
    private Integer name;
    private String information;
}
