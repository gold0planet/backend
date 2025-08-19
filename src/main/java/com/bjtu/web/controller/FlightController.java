package com.bjtu.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bjtu.web.common.PageResult;
import com.bjtu.web.common.Result;
import com.bjtu.web.model.Flight;
import com.bjtu.web.model.dto.Range;
import com.bjtu.web.model.vo.RangeCount;
import com.bjtu.web.service.FlightService;
import com.bjtu.web.service.HistoryRangeService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 航班控制器
 */
@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private HistoryRangeService historyRangeService;

    /**
     * 分页查询所有航班
     */
    @GetMapping("/page")
    public Result<PageResult<Flight>> getFlightsByPage(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        IPage<Flight> pageResult = flightService.getFlightsByPage(page, size);
        return Result.success(PageResult.of(pageResult));
    }

    /**
     * 按出生年份区间分页查询
     */
    @PostMapping("/birth-range")
    public Result<PageResult<Flight>> getFlightsByBirthRange(
        @RequestBody List<Range> ranges,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        // 将ranges存储数据库
        historyRangeService.saveHistoryRange(ranges, "birth");

        // 1. 查询分页数据
        IPage<Flight> pageResult = flightService.getFlightsByBirthRanges(page, size, ranges);
        // 2. 统计各区间的数量
        List<RangeCount> rangeCounts = flightService.countBirthRanges(ranges);
        // 3. 合并分页数据和区间统计数据
        PageResult<Flight> result = PageResult.of(pageResult, rangeCounts);
        return Result.success(result);
    }


    /**
     * 按里程区间分页查询
     */
    @PostMapping("/miles-range")
    public Result<PageResult<Flight>> getFlightsByMilesRange(
        @RequestBody List<Range> ranges,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        // 将ranges存储数据库
        historyRangeService.saveHistoryRange(ranges, "miles");
        // 1. 查询分页数据
        IPage<Flight> pageResult = flightService.getFlightsByMilesRange(page, size, ranges);
        // 2. 统计各区间的数量
        List<RangeCount> rangeCounts = flightService.countMilesRanges(ranges);
        // 3. 合并分页数据和区间统计数据
        PageResult<Flight> result = PageResult.of(pageResult, rangeCounts);
        return Result.success(result);
    }

    /**
     * 按飞行时间区间分页查询
     */
    @PostMapping("/hours-range")
    public Result<PageResult<Flight>> getFlightsByHoursRange(
        @RequestBody List<Range> ranges,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        // 将ranges存储数据库
        historyRangeService.saveHistoryRange(ranges, "hours");
        // 1. 查询分页数据
        IPage<Flight> pageResult = flightService.getFlightsByHoursRange(page, size, ranges);
        // 2. 统计各区间的数量
        List<RangeCount> rangeCounts = flightService.countHoursRanges(ranges);
        // 3. 合并分页数据和区间统计数据
        PageResult<Flight> result = PageResult.of(pageResult, rangeCounts);
        return Result.success(result);
    }
}
