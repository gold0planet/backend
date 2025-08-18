package com.bjtu.web.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 区间查询请求DTO
 */
@Data
public class RangeQueryRequest {
    private String rangeName;           // 区间名称（用于保存）
    private String rangeType;           // 区间类型（birth/miles/hours）
    private List<RangeItem> ranges;     // 区间列表
    private Boolean saveAsHistory;      // 是否保存为历史区间

    @Data
    public static class RangeItem {
        private Integer startValue;     // 起始值
        private Integer endValue;       // 结束值
    }
}
