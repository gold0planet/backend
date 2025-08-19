package com.bjtu.web.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 区间查询请求DTO
 */
@Data
public class RangeQueryRequest {
    private String rangeType;           // 区间类型（birth/miles/hours）
    private List<Range> ranges;     // 区间列表
}
