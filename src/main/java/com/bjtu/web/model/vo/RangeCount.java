package com.bjtu.web.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 区间统计结果模型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RangeCount {
    private Integer start;  // 区间起始值
    private Integer end;    // 区间结束值
    private Long count;     // 该区间的数据数量
}