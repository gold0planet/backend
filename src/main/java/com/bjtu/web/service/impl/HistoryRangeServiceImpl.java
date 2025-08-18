package com.bjtu.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bjtu.web.model.HistoryRange;
import com.bjtu.web.model.dto.RangeQueryRequest;
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
    public HistoryRange saveHistoryRange(RangeQueryRequest request) {
        HistoryRange historyRange = new HistoryRange();
        historyRange.setRangeName(request.getRangeName());
        historyRange.setRangeType(request.getRangeType());
        historyRange.setCreatedTime(LocalDateTime.now());

        // 如果只有一个区间，保存起始值和结束值
        if (request.getRanges() != null && !request.getRanges().isEmpty()) {
            RangeQueryRequest.RangeItem firstRange = request.getRanges().get(0);
            historyRange.setStartValue(firstRange.getStartValue());
            historyRange.setEndValue(firstRange.getEndValue());
        }

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
