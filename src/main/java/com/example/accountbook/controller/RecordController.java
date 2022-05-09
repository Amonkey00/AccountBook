package com.example.accountbook.controller;

import com.example.accountbook.entity.BillRecord;
import com.example.accountbook.entity.Role;
import com.example.accountbook.model.PageResult;
import com.example.accountbook.service.RecordService;
import com.example.accountbook.service.RoleService;
import com.example.accountbook.utils.JsonResult;
import com.example.accountbook.utils.JwtUtil;
import com.example.accountbook.vo.record.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

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

    @PostMapping("/getList")
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

    @PostMapping("/getPieData")
    public JsonResult getPieData(
            @RequestBody RecordListReqVo reqVo
    ) {
        try {
            List<RecordPieRespVo> pieData = recordService.getRecordPieData(reqVo);
            return new JsonResult(pieData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(-1, "获取饼图数据失败");
    }

    @PostMapping("/getLineData")
    public JsonResult getLineData(
            @RequestBody RecordListReqVo reqVo
    ) {
        try {
            List<RecordLineRespVo> lineData = recordService.getRecordLineData(reqVo);
            return new JsonResult(lineData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(-1, "获取折线图图数据失败");
    }

    @PostMapping("/update")
    public JsonResult update(
            HttpServletRequest request,
            @RequestBody RecordModifyReqVo reqVo
    ) {
        if (reqVo.getRecord() == null || reqVo.getRecord().getRId() == null) {
            return new JsonResult(-1,"record 信息出错");
        }
        String token = request.getHeader("token");
        Integer userId = JwtUtil.parseToken2Id(token);
        if (!checkRecordModify(userId, reqVo.getRecord())) {
            return new JsonResult(-1,"没有权限或关联组出错");
        }
        int flag = recordService.update(reqVo.getRecord());
        if (flag > 0) {
            return new JsonResult();
        }
        return new JsonResult(-1,"Record更新失败");
    }

    @PostMapping("/delete")
    public JsonResult delete(
            HttpServletRequest request,
            @RequestBody RecordModifyReqVo reqVo
    ) {
        if (reqVo.getRecord() == null || reqVo.getRecord().getRId() == null) {
            return new JsonResult(-1,"record 信息出错");
        }
        String token = request.getHeader("token");
        Integer userId = JwtUtil.parseToken2Id(token);
        if (!checkRecordModify(userId, reqVo.getRecord())) {
            return new JsonResult(-1,"没有权限或关联组出错");
        }
        int flag = recordService.delete(reqVo.getRecord());
        if (flag > 0) {
            return new JsonResult();
        }
        return new JsonResult(-1,"Record删除失败");
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

    private boolean checkRecordModify(Integer userId, BillRecord record) {
        if (userId == null || record == null) return false;
        // Creator of record can modify.
        if (userId.equals(record.getCreatorId())) return true;
        Role role = roleService.getRole(record.getGroupId(), userId);
        // No privilege
        if (role == null || role.getStatus() <= record.getStatus()) {
            return false;
        }
        return true;
    }
}
