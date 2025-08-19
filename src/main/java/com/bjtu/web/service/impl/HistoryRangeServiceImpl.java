package com.bjtu.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bjtu.web.model.HistoryRange;
import com.bjtu.web.model.dto.Range;
import com.bjtu.web.repository.HistoryRangeMapper;
import com.bjtu.web.service.HistoryRangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 历史区间服务实现
 */
@Service
public class HistoryRangeServiceImpl implements HistoryRangeService {

    @Autowired
    private HistoryRangeMapper historyRangeMapper;

    @Override
    public HistoryRange saveHistoryRange(List<Range> ranges, String rangeType) {
        HistoryRange historyRange = new HistoryRange();
        historyRange.setRangeType(rangeType);
        historyRange.setCreatedTime(LocalDateTime.now());

        // 组合起始值和结束值：start: 1,5,8   end: 5,8,10
        String startValue = "";
        String endValue = "";
        for (Range range : ranges) {
            startValue += range.getStart() + ",";
            endValue += range.getEnd() + ",";
        }
        historyRange.setStartValue(startValue);
        historyRange.setEndValue(endValue);
        historyRangeMapper.insert(historyRange);
        return historyRange;
    }

    @Override
    public HistoryRange getHistoryRangeById(Long id) {
        return historyRangeMapper.selectById(id);
    }

    @Override
    public IPage<HistoryRange> getHistoryRangesByPage(int page, int size, String rangeType, String keyword) {
        Page<HistoryRange> pageRequest = new Page<>(page, size);
        QueryWrapper<HistoryRange> queryWrapper = new QueryWrapper<>();

        if (StringUtils.hasText(rangeType)) {
            queryWrapper.eq("range_type", rangeType);
        }

        if (StringUtils.hasText(keyword)) {
            queryWrapper.and(wrapper -> wrapper
                .like("range_name", keyword)
                .or()
                .like("description", keyword)
            );
        }

        queryWrapper.orderByDesc("created_time");
        return historyRangeMapper.selectPage(pageRequest, queryWrapper);
    }

    @Override
    public List<HistoryRange> getHistoryRangesByType(String rangeType) {
        QueryWrapper<HistoryRange> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("range_type", rangeType)
                   .orderByDesc("use_count")
                   .orderByDesc("created_time");
        return historyRangeMapper.selectList(queryWrapper);
    }

    @Override
    public boolean deleteHistoryRange(Long id) {
        return historyRangeMapper.deleteById(id) > 0;
    }
}
