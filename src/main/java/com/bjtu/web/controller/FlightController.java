package com.bjtu.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bjtu.web.model.Flight;
import com.bjtu.web.mapper.FlightMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightMapper flightMapper;

    // 获取所有航班
    @GetMapping
    public List<Flight> getAllFlights() {
        return flightMapper.selectList(null);
    }

    // 根据ID获取特定航班
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Integer id) {
        Flight flight = flightMapper.selectById(id);
        return flight != null ? 
            ResponseEntity.ok(flight) : 
            ResponseEntity.notFound().build();
    }

    // 根据性别查询航班
    @GetMapping("/sex/{sex}")
    public List<Flight> getFlightsBySex(@PathVariable Integer sex) {
        QueryWrapper<Flight> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sex", sex);
        return flightMapper.selectList(queryWrapper);
    }

    // 根据出生年份查询航班
    @GetMapping("/birth/{birth}")
    public List<Flight> getFlightsByBirth(@PathVariable Integer birth) {
        QueryWrapper<Flight> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("birth", birth);
        return flightMapper.selectList(queryWrapper);
    }

    // 查询里程大于指定值的航班
    @GetMapping("/miles/gt/{miles}")
    public List<Flight> getFlightsByMilesGreaterThan(@PathVariable Integer miles) {
        QueryWrapper<Flight> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("miles", miles);
        return flightMapper.selectList(queryWrapper);
    }

    // 查询飞行小时数大于指定值的航班
    @GetMapping("/hours/gt/{hours}")
    public List<Flight> getFlightsByHoursGreaterThan(@PathVariable Integer hours) {
        QueryWrapper<Flight> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("hours", hours);
        return flightMapper.selectList(queryWrapper);
    }

    // 创建新航班
    @PostMapping
    public Flight createFlight(@RequestBody Flight flight) {
        flightMapper.insert(flight);
        return flight;
    }

    // 更新航班信息
    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Integer id, @RequestBody Flight flightDetails) {
        Flight existingFlight = flightMapper.selectById(id);
        
        if (existingFlight != null) {
            flightDetails.setId(id);
            flightMapper.updateById(flightDetails);
            return ResponseEntity.ok(flightDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 删除航班
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Integer id) {
        int result = flightMapper.deleteById(id);
        return result > 0 ? 
            ResponseEntity.ok().build() : 
            ResponseEntity.notFound().build();
    }
}
