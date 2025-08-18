package com.bjtu.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bjtu.web.common.PageResult;
import com.bjtu.web.common.Result;
import com.bjtu.web.model.HistoryRange;
import com.bjtu.web.model.dto.RangeQueryRequest;
import com.bjtu.web.service.HistoryRangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 历史区间控制器
 */
@RestController
@RequestMapping("/history-ranges")
public class HistoryRangeController {

    @Autowired
    private HistoryRangeService historyRangeService;

    /**
     * 保存历史区间
     */
    @PostMapping
    public Result<HistoryRange> saveHistoryRange(@RequestBody RangeQueryRequest request) {
        if (request.getSaveAsHistory() == null || !request.getSaveAsHistory()) {
            return Result.error("未指定保存为历史区间");
        }
        
        HistoryRange historyRange = historyRangeService.saveHistoryRange(request);
        return Result.success(historyRange);
    }

    /**
     * 根据ID获取历史区间
     */
    @GetMapping("/{id}")
    public Result<HistoryRange> getHistoryRangeById(@PathVariable Long id) {
        HistoryRange historyRange = historyRangeService.getHistoryRangeById(id);
        return historyRange != null ? Result.success(historyRange) : Result.error("历史区间不存在");
    }

    /**
     * 分页查询历史区间
     */
    @GetMapping("/page")
    public Result<PageResult<HistoryRange>> getHistoryRangesByPage(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "20") int size,
        @RequestParam(required = false) String rangeType,
        @RequestParam(required = false) String keyword
    ) {
        IPage<HistoryRange> pageResult = historyRangeService.getHistoryRangesByPage(page, size, rangeType, keyword);
        return Result.success(PageResult.of(pageResult));
    }

    /**
     * 根据类型查询历史区间
     */
    @GetMapping("/type/{rangeType}")
    public Result<List<HistoryRange>> getHistoryRangesByType(@PathVariable String rangeType) {
        List<HistoryRange> historyRanges = historyRangeService.getHistoryRangesByType(rangeType);
        return Result.success(historyRanges);
    }

    /**
     * 删除历史区间
     */
    @DeleteMapping("/{id}")
    public Result<String> deleteHistoryRange(@PathVariable Long id) {
        boolean success = historyRangeService.deleteHistoryRange(id);
        return success ? Result.success("删除成功") : Result.error("删除失败");
    }
}
