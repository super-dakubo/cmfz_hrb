package com.baizhi.dao;

import com.baizhi.entity.Essay;
import java.util.List;

public interface EssayMapper {
    int deleteByPrimaryKey(String id);

    int insert(Essay record);

    Essay selectByPrimaryKey(String id);

    List<Essay> selectAll();

    int updateByPrimaryKey(Essay record);
}