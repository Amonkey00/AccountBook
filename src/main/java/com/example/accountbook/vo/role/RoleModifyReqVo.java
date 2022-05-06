package com.example.accountbook.vo.role;

import lombok.Data;

@Data
public class RoleModifyReqVo {
    private Integer operatorId;
    private Integer targetId;
    private Integer groupId;
    private Integer status;
}
