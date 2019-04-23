package com.baizhi.dao;

import com.baizhi.entity.Counter;
import java.util.List;

public interface CounterMapper {
    int deleteByPrimaryKey(String id);

    int insert(Counter record);

    Counter selectByPrimaryKey(String id);

    List<Counter> selectAll();

    int updateByPrimaryKey(Counter record);
}