package com.baizhi.dao;

import com.baizhi.entity.Province;

import java.util.List;

public interface ProvinceMapper {
    List<Province> selectAll(String sex);
}
