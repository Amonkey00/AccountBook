package com.example.accountbook.vo.record;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RecordCreateReqVo implements Serializable {
    private Integer groupId;
    private Integer typeId;
    private String name;
    private Date recordTime;
    private Double amount;
    private String information;
    private Integer creatorId;
    private String creatorName;
    private Integer status;
}
