package com.example.accountbook.service.impl;

import com.example.accountbook.dao.RecordMapper;
import com.example.accountbook.entity.BillRecord;
import com.example.accountbook.model.PageResult;
import com.example.accountbook.service.RecordService;
import com.example.accountbook.vo.record.RecordListReqVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Resource
    RecordMapper recordMapper;

    @Override
    public int add(BillRecord record) {
        return recordMapper.insert(record);
    }

    @Override
    public int update(BillRecord record) {
        return recordMapper.updateById(record);
    }

    @Override
    public int delete(BillRecord record) {
        return recordMapper.deleteById(record);
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
}
