package com.example.accountbook.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("type_info")
public class ColumnType {
    private int tId;
    private int name;
    private String information;
}
