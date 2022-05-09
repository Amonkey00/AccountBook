package com.example.accountbook.vo.type;

import lombok.Data;

@Data
public class TypeCreateReqVo {
    private Integer groupId;
    private String name;
    private String information;
    private String kind;
}
