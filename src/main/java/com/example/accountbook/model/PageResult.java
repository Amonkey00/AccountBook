package com.example.accountbook.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PageResult<T> {
    List<T> dataList;
    Integer pageSize;
    Integer total;

    public PageResult(List<T> list) {
        this.dataList = list;
    }

    public PageResult(Page<T> page) {
        this.dataList = page.getRecords();
        this.pageSize = Math.toIntExact(page.getPages());
        this.total = Math.toIntExact(page.getTotal());
    }

    public void parsePage(Integer total, Integer size) {
        this.total = total;
        int pageNum = total / size;
        if (total % size != 0) {
            pageNum += 1;
        }
        this.pageSize = pageNum;
    }

}
