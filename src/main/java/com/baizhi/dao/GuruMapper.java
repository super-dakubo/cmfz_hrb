package com.baizhi.dao;

import com.baizhi.entity.Guru;
import java.util.List;

public interface GuruMapper {
    int deleteByPrimaryKey(String id);

    int insert(Guru record);

    Guru selectByPrimaryKey(String id);

    List<Guru> selectAll();

    int updateByPrimaryKey(Guru record);
}