package com.baizhi.dao;

import com.baizhi.entity.Lesson;
import java.util.List;

public interface LessonMapper {
    int deleteByPrimaryKey(String id);

    int insert(Lesson record);

    Lesson selectByPrimaryKey(String id);

    List<Lesson> selectAll();

    int updateByPrimaryKey(Lesson record);
}