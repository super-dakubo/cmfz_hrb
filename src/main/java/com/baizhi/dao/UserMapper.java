package com.baizhi.dao;

import com.baizhi.entity.User;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    Integer countByDate(Integer days);
}