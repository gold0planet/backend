package com.bjtu.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bjtu.web.model.Flight;
import com.bjtu.web.model.dto.Range;
import com.bjtu.web.model.vo.RangeCount;
import com.bjtu.web.repository.FlightMapper;
import com.bjtu.web.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 航班服务实现类
 */
@Service
public class FlightServiceImpl implements FlightService {
    
    @Autowired
    private FlightMapper flightMapper;
    
    @Override
    public IPage<Flight> getFlightsByPage(int page, int size) {
        Page<Flight> pageRequest = new Page<>(page, size);
        return flightMapper.selectPage(pageRequest, null);
    }
    
    @Override
    public IPage<Flight> getFlightsByBirthRanges(int page, int size, List<Range> ranges) {
        Page<Flight> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<Flight> queryWrapper = new LambdaQueryWrapper<>();
        // 构建多区间查询条件（OR关系）
        for (int i = 0; i < ranges.size(); i++) {
            Range range = ranges.get(i);
            if (i == 0) {
                queryWrapper.between(Flight::getBirth, range.getStart(), range.getEnd());
            } else {
                queryWrapper.or().between(Flight::getBirth, range.getStart(), range.getEnd());
            }
        }
        return flightMapper.selectPage(pageParam, queryWrapper);
    }

    // 出生年份区间统计
    @Override
    public List<RangeCount> countBirthRanges(List<Range> ranges) {
        List<RangeCount> result = new ArrayList<>();
        for (Range range : ranges) {
            // 统计单个区间的数量
            LambdaQueryWrapper<Flight> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.between(Flight::getBirth, range.getStart(), range.getEnd());
            long count = flightMapper.selectCount(queryWrapper);
            result.add(new RangeCount(range.getStart(), range.getEnd(), count));
        }
        return result;
    }

    @Override
    public IPage<Flight> getFlightsByMilesRange(int page, int size, List<Range> ranges) {
        Page<Flight> pageRequest = new Page<>(page, size);
        QueryWrapper<Flight> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("miles", ranges.get(0).getStart(), ranges.get(0).getEnd()).orderByAsc("miles");
        return flightMapper.selectPage(pageRequest, queryWrapper);
    }

    // 里程区间统计
    @Override
    public List<RangeCount> countMilesRanges(List<Range> ranges) {
        List<RangeCount> result = new ArrayList<>();
        for (Range range : ranges) {
            LambdaQueryWrapper<Flight> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.between(Flight::getMiles, range.getStart(), range.getEnd());
            long count = flightMapper.selectCount(queryWrapper);
            result.add(new RangeCount(range.getStart(), range.getEnd(), count));
        }
        return result;
    }

    @Override
    public IPage<Flight> getFlightsByHoursRange(int page, int size, List<Range> ranges) {
        Page<Flight> pageRequest = new Page<>(page, size);
        QueryWrapper<Flight> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("hours", ranges.get(0).getStart(), ranges.get(0).getEnd()).orderByAsc("hours");
        return flightMapper.selectPage(pageRequest, queryWrapper);
    }

    // 飞行时间区间统计
    @Override
    public List<RangeCount> countHoursRanges(List<Range> ranges) {
        List<RangeCount> result = new ArrayList<>();
        for (Range range : ranges) {
            LambdaQueryWrapper<Flight> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.between(Flight::getHours, range.getStart(), range.getEnd());
            long count = flightMapper.selectCount(queryWrapper);
            result.add(new RangeCount(range.getStart(), range.getEnd(), count));
        }
        return result;
    }
}
