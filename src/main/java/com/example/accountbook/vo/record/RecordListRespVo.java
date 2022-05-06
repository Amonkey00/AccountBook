package com.example.accountbook.vo.record;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RecordListRespVo implements Serializable {
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
}
