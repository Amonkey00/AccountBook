package com.example.accountbook.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.accountbook.dao.TypeMapper;
import com.example.accountbook.entity.ColumnType;
import com.example.accountbook.model.PageResult;
import com.example.accountbook.service.TypeService;
import org.apache.commons.lang.StringUtils;
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
    public int update(ColumnType type) {
        return typeMapper.updateById(type);
    }

    @Override
    public ColumnType getTypeById(Integer typeId) {
        if (typeId == null) return null;
        return typeMapper.selectById(typeId);
    }

    @Override
    public ColumnType getTypeByName(Integer groupId, String name) {
        if (name == null) return null;
        QueryWrapper<ColumnType> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        wrapper.eq("group_id", groupId);
        try {
            return typeMapper.selectOne(wrapper);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ColumnType> getTypeList(Integer groupId) {
        List<ColumnType> resultList = Collections.emptyList();
        try {
            QueryWrapper<ColumnType> wrapper = new QueryWrapper<>();
            wrapper.eq("group_id", groupId);
            resultList = typeMapper.selectList(wrapper);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

}
