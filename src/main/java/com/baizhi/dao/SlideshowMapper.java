package com.baizhi.dao;

import com.baizhi.entity.Slideshow;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SlideshowMapper extends Mapper<Slideshow> {
        void insertList(@Param("list") List<Slideshow> list);
}