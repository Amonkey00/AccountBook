package com.example.accountbook.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("group_info")
public class BillGroup {
    private int gId;
    private String groupName;
    private String groupInfo;
    private int creatorId;
    private String creator_name;
    private Date createTime;
}
