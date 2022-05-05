package com.example.accountbook.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("bill_record")
public class BillRecord {
    @TableId(value="r_id", type= IdType.AUTO)
    private Integer rId;
    private Integer groupId;
    private Integer typeId;
    private String name;
    private Date recordTime;
    private Double amount;
    private String information;
    private Integer creatorId;
    private String creatorName;
    private Date createTime;
    private Integer status;
}
