package com.example.accountbook.controller;

import com.example.accountbook.entity.Group;
import com.example.accountbook.utils.JsonResult;
import com.example.accountbook.vo.group.GroupCreateReqVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group")
public class GroupController {

    @PostMapping("/create")
    public JsonResult create(
            @RequestBody GroupCreateReqVo reqVo
    ) {
        Group group = buildGroup(reqVo);
        if (group == null) {
            return new JsonResult(-1,"Group信息有误");
        }
        return new JsonResult(-1,"Error");
    }





    // ================ Utils =================
    private Group buildGroup(GroupCreateReqVo reqVo) {
        if (StringUtils.isBlank(reqVo.getGroupName()) ||
                StringUtils.isBlank(reqVo.getCreatorName()) ||
                reqVo.getCreatorId() == null
        ) {
            return null;
        }
        Group group = new Group();
        BeanUtils.copyProperties(reqVo,group);
        return group;
    }
}
