package com.example.accountbook.vo.record;

import lombok.Data;

import java.util.Date;

@Data
public class RecordLineRespVo {
    Integer typeId;
    Date recordTime;
    Double totalAmount;
}
