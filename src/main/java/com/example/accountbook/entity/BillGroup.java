package com.example.accountbook.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("group_info")
public class BillGroup {
    @TableId(value="g_id", type= IdType.AUTO)
    private Integer gId;
    private String groupName;
    private String groupInfo;
    private Integer creatorId;
    private String creator_name;
    private Date createTime;
}
