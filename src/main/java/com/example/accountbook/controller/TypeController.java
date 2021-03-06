package com.example.accountbook.controller;

import com.example.accountbook.entity.ColumnType;
import com.example.accountbook.model.PageResult;
import com.example.accountbook.service.TypeService;
import com.example.accountbook.utils.JsonResult;
import com.example.accountbook.vo.type.TypeCreateReqVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    TypeService typeService;

    @PostMapping("/create")
    public JsonResult create(
            @RequestBody TypeCreateReqVo reqVo
    ) {
        ColumnType type = buildType(reqVo);
        if (type == null) {
            return new JsonResult(-1,"Type 输入信息有误");
        }
        ColumnType existsType = typeService.getTypeByName(reqVo.getGroupId(), reqVo.getName());
        if (existsType != null) {
            return new JsonResult(-1,"该种类已经创建");
        }
        int flag = typeService.add(type);
        if (flag > 0) {
            return new JsonResult();
        }
        return new JsonResult(-1,"Type Insert失败");
    }

    @PostMapping("/update")
    public JsonResult update(
            @RequestBody ColumnType type
    ) {
        if (StringUtils.isBlank(type.getName()) || StringUtils.isBlank(type.getInformation())) {
            return new JsonResult(-1, "更新数据有误");
        }
        // check eixsts
        ColumnType existsType = typeService.getTypeByName(type.getGroupId(), type.getName());
        if (existsType != null && !existsType.getTId().equals(type.getTId())) {
            return new JsonResult(-1, "与组内标签名重复");
        }
        int flag = typeService.update(type);
        if (flag > 0) {
            return new JsonResult();
        }
        return new JsonResult(-1, "Type Update失败");

    }

    @GetMapping("/get")
    public JsonResult get(
            @RequestParam("typeId") Integer typeId
    ) {
        try {
            ColumnType type = typeService.getTypeById(typeId);
            return new JsonResult(type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(-1,"Type 获取失败");
    }

    @GetMapping("/getList")
    public JsonResult getList(
            @RequestParam("groupId") Integer groupId
    ) {
        try {
            List<ColumnType> typeList = typeService.getTypeList(groupId);
            return new JsonResult(typeList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(-1, "全量Type 获取失败");
    }






    // ================== Utils ===================
    private ColumnType buildType(TypeCreateReqVo reqVo) {
        if (reqVo.getGroupId() == null ||
                StringUtils.isBlank(reqVo.getName()) ||
                StringUtils.isBlank(reqVo.getInformation())) {
            return null;
        }
        ColumnType columnType = new ColumnType();
        BeanUtils.copyProperties(reqVo, columnType);
        return columnType;
    }
}
