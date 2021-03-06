package com.example.accountbook.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.accountbook.entity.BillRecord;
import com.example.accountbook.vo.record.RecordLineRespVo;
import com.example.accountbook.vo.record.RecordListReqVo;
import com.example.accountbook.vo.record.RecordPieRespVo;
import com.example.accountbook.vo.record.RecordTotalDayVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecordMapper extends BaseMapper<BillRecord> {

    int countRecordList(RecordListReqVo reqVo);
    List<BillRecord> queryRecordList(RecordListReqVo reqVo);

    Double computeTotalAmount(RecordListReqVo reqVo);
    List<RecordPieRespVo> queryRecordPie(RecordListReqVo reqVo);
    List<RecordLineRespVo> queryRecordLine(RecordListReqVo reqVo);

    List<RecordTotalDayVo> queryListByDay(@Param("groupId") Integer groupId,
                                          @Param("order") String order,
                                          @Param("limit") Integer limit,
                                          @Param("fromDate") String fromDate,
                                          @Param("toDate") String toDate);
}
