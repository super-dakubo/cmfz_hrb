package com.baizhi.service.impl;

import com.baizhi.dao.ProvinceMapper;
import com.baizhi.dao.UserMapper;
import com.baizhi.entity.Province;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProvinceMapper provinceMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, String> login(User user) {
        return null;
    }

    @Override
    public Map<String, String> regist(User user) {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, List<Province>> selectAllProvince() {
        Map<String, List<Province>> map = new HashMap<>();
        List<Province> man = provinceMapper.selectAll("男");
        List<Province> woman = provinceMapper.selectAll("女");
        map.put("man",man);
        map.put("woman",woman);
        return map;
    }

    @Override
    public List<Integer> countBydate() {
        List<Integer> dates = new ArrayList<>();
        dates.add(userMapper.countByDate(1));
        dates.add(userMapper.countByDate(7));
        dates.add(userMapper.countByDate(30));
        dates.add(userMapper.countByDate(90));
        dates.add(userMapper.countByDate(180));
        return dates;
    }
}
