package com.example.accountbook.service;

import com.example.accountbook.entity.BillRecord;
import com.example.accountbook.model.PageResult;
import com.example.accountbook.vo.record.RecordLineRespVo;
import com.example.accountbook.vo.record.RecordListReqVo;
import com.example.accountbook.vo.record.RecordPieRespVo;

import java.util.List;

public interface RecordService {

    int add(BillRecord record);

    int update(BillRecord record);

    int delete(BillRecord record);

    BillRecord getRecordById(Integer recordId);

    PageResult<BillRecord> getRecordList(RecordListReqVo reqVo);

    List<RecordPieRespVo> getRecordPieData(RecordListReqVo reqVo);
    List<RecordLineRespVo> getRecordLineData(RecordListReqVo reqVo);

}
