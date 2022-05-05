package com.example.accountbook.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.accountbook.entity.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GroupMapper extends BaseMapper<Group> {

    Integer countGroupList(@Param("userId") Integer userId);

    List<Group> queryGroupList(@Param("userId") Integer userId,
                               @Param("start") Integer start,
                               @Param("size") Integer size);
}
