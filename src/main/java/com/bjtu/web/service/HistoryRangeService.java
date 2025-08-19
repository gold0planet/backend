package com.bjtu.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bjtu.web.model.HistoryRange;
import com.bjtu.web.model.dto.Range;

import java.util.List;

/**
 * 历史区间服务接口
 */
public interface HistoryRangeService {
    
    /**
     * 保存历史区间
     */
    HistoryRange saveHistoryRange(List<Range> ranges, String rangeType);
    
    /**
     * 根据ID获取历史区间
     */
    HistoryRange getHistoryRangeById(Long id);
    
    /**
     * 分页查询历史区间
     */
    IPage<HistoryRange> getHistoryRangesByPage(int page, int size, String rangeType, String keyword);
    
    /**
     * 根据类型查询历史区间
     */
    List<HistoryRange> getHistoryRangesByType(String rangeType);
    
    /**
     * 删除历史区间
     */
    boolean deleteHistoryRange(Long id);
}
