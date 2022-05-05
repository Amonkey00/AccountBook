package com.example.accountbook.vo.group;

import lombok.Data;

@Data
public class GroupCreateReqVo {

    private String groupName;
    private String groupInfo;
    private Integer creatorId;
    private String creatorName;

}
