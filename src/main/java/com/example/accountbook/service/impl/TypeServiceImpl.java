package com.example.accountbook.service.impl;

import com.example.accountbook.dao.TypeMapper;
import com.example.accountbook.entity.ColumnType;
import com.example.accountbook.service.TypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Resource
    TypeMapper typeMapper;

    @Override
    public int add(ColumnType type) {
        return typeMapper.insert(type);
    }

    @Override
    public List<ColumnType> getTypeList(Integer groupId) {
        List<ColumnType> resultList = Collections.emptyList();
        try {
            resultList = typeMapper.queryTypeList(groupId);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
