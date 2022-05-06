package com.example.accountbook.vo.role;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleModifyReqVo implements Serializable {
    private Integer operatorId;
    private Integer targetId;
    private Integer groupId;
    private Integer status;
}
