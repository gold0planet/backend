package com.bjtu.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bjtu.web.model.Flight;
import com.bjtu.web.model.dto.Range;
import com.bjtu.web.model.vo.RangeCount;

import java.util.List;

/**
 * 航班服务接口
 */
public interface FlightService {
    
    /**
     * 分页查询所有航班
     */
    IPage<Flight> getFlightsByPage(int page, int size);
    
    /**
     * 按出生年份区间分页查询
     */
    IPage<Flight> getFlightsByBirthRanges(int page, int size, List<Range> ranges);
    
    // 统计各出生年份区间的数量
    List<RangeCount> countBirthRanges(List<Range> ranges);

    /**
     * 按里程区间分页查询
     */
    IPage<Flight> getFlightsByMilesRange(int page, int size, List<Range> ranges);
    
    // 统计各里程区间的数量
    List<RangeCount> countMilesRanges(List<Range> ranges);

    /**
     * 按飞行时间区间分页查询
     */
    IPage<Flight> getFlightsByHoursRange(int page, int size, List<Range> ranges);

    // 统计各飞行时间区间的数量
    List<RangeCount> countHoursRanges(List<Range> ranges);
}
