package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ChapterMapper extends Mapper<Chapter> {
    List<Chapter> select(Chapter chapter);
    int insert(Chapter chapter);
    void update(Chapter chapter);
    void updateCount(Chapter chapter);
}