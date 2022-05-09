package com.example.accountbook.service;

import com.example.accountbook.entity.ColumnType;
import com.example.accountbook.model.PageResult;

import java.util.List;

public interface TypeService {

    int add(ColumnType type);
    int update(ColumnType type);

    ColumnType getTypeById(Integer typeId);
    ColumnType getTypeByName(Integer groupId, String name);


    /**
     * 获取对应groupId 下所有的type类型
     */
    List<ColumnType> getTypeList(Integer groupId);

}
