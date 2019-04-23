package com.baizhi.service;

import com.baizhi.entity.Province;
import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String,String> login(User user);
    Map<String,String> regist(User user);
    Map<String, List<Province>> selectAllProvince();
    List<Integer> countBydate();
}
