package com.example.accountbook.service;

import com.example.accountbook.entity.BillRecord;
import com.example.accountbook.model.PageResult;
import com.example.accountbook.vo.record.RecordLineRespVo;
import com.example.accountbook.vo.record.RecordListReqVo;
import com.example.accountbook.vo.record.RecordPieRespVo;
import com.example.accountbook.vo.record.RecordTotalDayVo;

import java.util.List;

public interface RecordService {

    int add(BillRecord record);

    int update(BillRecord record);

    int delete(BillRecord record);

    BillRecord getRecordById(Integer recordId);

    PageResult<BillRecord> getRecordList(RecordListReqVo reqVo);

    Double computeTotalAmount(RecordListReqVo reqVo);

    List<RecordPieRespVo> getRecordPieData(RecordListReqVo reqVo);
    List<RecordLineRespVo> getRecordLineData(RecordListReqVo reqVo);
    List<RecordTotalDayVo> getTotalByDay(Integer groupId, String order,
                                         Integer limit, String fromDate, String toDate);
}
