package com.bjtu.web.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjtu.web.model.HistoryRange;
import org.apache.ibatis.annotations.Mapper;

/**
 * 历史区间 Mapper
 */
@Mapper
public interface HistoryRangeMapper extends BaseMapper<HistoryRange> {
}
