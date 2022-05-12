package com.example.accountbook.service.impl;

import com.example.accountbook.dao.RecordMapper;
import com.example.accountbook.entity.BillRecord;
import com.example.accountbook.model.PageResult;
import com.example.accountbook.service.RecordService;
import com.example.accountbook.vo.record.RecordLineRespVo;
import com.example.accountbook.vo.record.RecordListReqVo;
import com.example.accountbook.vo.record.RecordPieRespVo;
import com.example.accountbook.vo.record.RecordTotalDayVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Resource
    RecordMapper recordMapper;

    @Override
    public int add(BillRecord record) {
        try {
            return recordMapper.insert(record);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int update(BillRecord record) {
        try {
            return recordMapper.updateById(record);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delete(BillRecord record) {
        try {
            return recordMapper.deleteById(record);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public BillRecord getRecordById(Integer recordId) {
        if (recordId == null) return null;
        return recordMapper.selectById(recordId);
    }

    @Override
    public PageResult<BillRecord> getRecordList(RecordListReqVo reqVo) {
        PageResult<BillRecord> result = new PageResult<>();

        int start = Math.max((reqVo.getStart() - 1) * reqVo.getSize(), 0);
        reqVo.setStart(start);

        // Query Total
        int total = recordMapper.countRecordList(reqVo);
        // Query Data
        List<BillRecord> billRecords = recordMapper.queryRecordList(reqVo);
        result.setDataList(billRecords);

        // Compute PageNum
        result.parsePage(total, reqVo.getSize());
        return result;
    }

    @Override
    public Double computeTotalAmount(RecordListReqVo reqVo) {
        return recordMapper.computeTotalAmount(reqVo);
    }

    @Override
    public List<RecordPieRespVo> getRecordPieData(RecordListReqVo reqVo) {
        return recordMapper.queryRecordPie(reqVo);
    }

    @Override
    public List<RecordLineRespVo> getRecordLineData(RecordListReqVo reqVo) {
        return recordMapper.queryRecordLine(reqVo);
    }

    @Override
    public List<RecordTotalDayVo> getTotalByDay(Integer groupId, String order, Integer limit, String fromDate, String toDate) {
        if (StringUtils.isBlank(order)) order = "DESC";
        return recordMapper.queryListByDay(groupId, order, limit, fromDate, toDate);
    }
}
