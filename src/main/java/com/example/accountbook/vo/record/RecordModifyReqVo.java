package com.example.accountbook.vo.record;

import com.example.accountbook.entity.BillRecord;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RecordModifyReqVo implements Serializable {
    Integer userId;
    BillRecord record;
}
