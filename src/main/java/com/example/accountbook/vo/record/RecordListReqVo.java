package com.example.accountbook.vo.record;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class RecordListReqVo implements Serializable {
    Integer userId;
    Integer groupId;
    String fromDate;
    String toDate;
    List<Integer> userIds;
    List<Integer> typeIds;
    Integer start;
    Integer size;
}
