package com.bjtu.web.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bjtu.web.model.RangeCount;

import lombok.Data;

import java.util.List;

/**
 * 分页响应结果
 */
@Data
public class PageResult<T> {
    private List<T> records;    // 数据列表
    private Long total;         // 总记录数
    private Long current;       // 当前页
    private Long size;          // 每页记录数
    private Long pages;         // 总页数
    private List<RangeCount> rangeCounts; // 区间统计数据
    
    public PageResult(IPage<T> page) {
        this.records = page.getRecords();
        this.total = page.getTotal();
        this.current = page.getCurrent();
        this.size = page.getSize();
        this.pages = page.getPages();
    }
    
    // 新增构造方法：支持传入区间统计数据
    public PageResult(IPage<T> page, List<RangeCount> rangeCounts) {
        this.records = page.getRecords();
        this.total = page.getTotal();
        this.current = page.getCurrent();
        this.size = page.getSize();
        this.pages = page.getPages();
        this.rangeCounts = rangeCounts; // 赋值区间统计数据
    }

    /**
     * 创建带区间统计的分页结果
     */
    public static <T> PageResult<T> of(IPage<T> page, List<RangeCount> rangeCounts) {
        return new PageResult<>(page, rangeCounts);
    }

    /**
     * 创建分页结果
     */
    public static <T> PageResult<T> of(IPage<T> page) {
        return new PageResult<>(page);
    }
}
