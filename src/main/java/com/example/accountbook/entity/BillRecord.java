package com.example.accountbook.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("bill_record")
public class BillRecord {
    private int rId;
    private int groupId;
    private int typeId;
    private String name;
    private Date recordTime;
    private Double amount;
    private String information;
    private int creatorId;
    private String creatorName;
    private Date createTime;
    private int status;
}
