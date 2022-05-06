package com.example.accountbook.controller;

import com.example.accountbook.entity.BillRecord;
import com.example.accountbook.entity.Role;
import com.example.accountbook.model.PageResult;
import com.example.accountbook.service.RecordService;
import com.example.accountbook.service.RoleService;
import com.example.accountbook.utils.JsonResult;
import com.example.accountbook.vo.record.RecordCreateReqVo;
import com.example.accountbook.vo.record.RecordListReqVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    RecordService recordService;
    @Autowired
    RoleService roleService;


    @PostMapping("/create")
    public JsonResult create(
            @RequestBody RecordCreateReqVo reqVo
    ) {
        BillRecord record = buildRecord(reqVo);
        if (record == null) {
            return new JsonResult(-1,"Record创建数据有误");
        }
        // check privilege
        if (!checkRecordCreate(record)) {
            return new JsonResult(-1,"没有该权限");
        }
        int flag = recordService.add(record);
        if (flag > 0) {
            return new JsonResult();
        }
        return new JsonResult(-1,"Record Insert失败");
    }

    @GetMapping("/get")
    public JsonResult get(
            @RequestParam("recordId") Integer recordId
    ) {
        try {
            BillRecord record = recordService.getRecordById(recordId);
            return new JsonResult(record);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(-1,"get 获取失败");
        }
    }

    @GetMapping("/getList")
    public JsonResult getList(
            @RequestBody RecordListReqVo reqVo
    ) {
        try {
            PageResult<BillRecord> recordList = recordService.getRecordList(reqVo);
            return new JsonResult(recordList);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(-1,"getList 获取失败");
        }
    }









    // =============== Utils ==============
    private BillRecord buildRecord(RecordCreateReqVo reqVo) {

        // Validate
        if (reqVo.getRecordTime() == null || reqVo.getAmount() == null ||
           reqVo.getCreatorId() == null) {
            return null;
        }

        BillRecord billRecord = new BillRecord();
        BeanUtils.copyProperties(reqVo, billRecord);
        return billRecord;
    }

    private boolean checkRecordCreate(BillRecord record) {
        Role role = roleService.getRole(record.getGroupId(), record.getCreatorId());
        if (role == null) return false;
        record.setStatus(role.getStatus());
        return true;
    }
}
