package com.bjtu.web.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 历史区间实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("history_range")
public class HistoryRange implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("range_name")
    private String rangeName;           // 区间名称

    @TableField("range_type")
    private String rangeType;           // 区间类型（birth/miles/hours）

    @TableField("start_value")
    private Integer startValue;         // 起始值

    @TableField("end_value")
    private Integer endValue;           // 结束值

    @TableField("created_time")
    private LocalDateTime createdTime;  // 创建时间
}
