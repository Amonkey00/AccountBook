package com.example.accountbook.service;

import com.example.accountbook.entity.ColumnType;

import java.util.List;

public interface TypeService {

    int add(ColumnType type);

    /**
     * 获取对应groupId 下所有的type类型
     */
    List<ColumnType> getTypeList(Integer groupId);

}
