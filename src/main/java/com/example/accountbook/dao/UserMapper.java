package com.example.accountbook.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.accountbook.entity.User;
import com.example.accountbook.vo.user.MemberListVo;
import com.example.accountbook.vo.user.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    Integer countUserList(@Param("groupId") Integer groupId,
                          @Param("role") Integer role);

    List<User> queryUserList(@Param("groupId") Integer groupId,
                             @Param("role") Integer role,
                             @Param("start") Integer start,
                             @Param("limit") Integer limit);

    Integer countMemberList(@Param("groupId") Integer groupId,
                                       @Param("keyword") String keyword,
                                       @Param("role") Integer role);

    List<MemberListVo> queryMemberList(@Param("groupId") Integer groupId,
                                       @Param("keyword") String keyword,
                                       @Param("roleStatus") Integer role,
                                       @Param("start") Integer start,
                                       @Param("limit") Integer limit);

    Integer updateUser(UserInfoVo userInfoVo);
}
