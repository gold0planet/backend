package com.bjtu.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjtu.web.model.Flight;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FlightMapper extends BaseMapper<Flight> {
    // 自定义查询方法
    
    // 根据性别查询航班
    List<Flight> selectBySex(Integer sex);

    // 根据出生年份查询航班
    List<Flight> selectByBirth(Integer birth);

    // 查询里程大于指定值的航班
    List<Flight> selectByMilesGreaterThan(Integer miles);

    // 查询飞行小时数大于指定值的航班
    List<Flight> selectByHoursGreaterThan(Integer hours);
}
