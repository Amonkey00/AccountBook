package com.example.accountbook.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.accountbook.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    Integer countUserList(@Param("groupId") Integer groupId);

    List<User> queryUserList(@Param("groupId") Integer groupId,
                             @Param("start") Integer start,
                             @Param("limit") Integer limit);
}
