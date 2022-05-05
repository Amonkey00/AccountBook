package com.example.accountbook.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.accountbook.entity.BillGroup;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface GroupMapper extends BaseMapper<BillGroup> {

}