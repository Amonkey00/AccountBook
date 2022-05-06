package com.example.accountbook.vo.group;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupCreateReqVo implements Serializable {

    private String groupName;
    private String groupInfo;
    private Integer creatorId;
    private String creatorName;

}
