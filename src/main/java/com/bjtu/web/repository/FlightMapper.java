package com.bjtu.web.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjtu.web.model.Flight;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FlightMapper extends BaseMapper<Flight> {
    // MyBatis-Plus 已经提供了大量内置方法：
    // selectById(Serializable id) - 根据 ID 查询
    // selectList(Wrapper<T> queryWrapper) - 条件查询
    // selectPage(IPage<T> page, Wrapper<T> queryWrapper) - 分页查询
    // insert(T entity) - 插入
    // updateById(T entity) - 根据 ID 更新
    // deleteById(Serializable id) - 根据 ID 删除
    // 等等...
    
    // 无需定义任何方法，直接继承 BaseMapper 即可使用所有 CRUD 功能
}
