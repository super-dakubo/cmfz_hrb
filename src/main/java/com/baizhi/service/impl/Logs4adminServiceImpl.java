package com.baizhi.service.impl;

import com.baizhi.dao.Logs4adminMapper;
import com.baizhi.entity.Logs4admin;
import com.baizhi.service.Logs4adminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional
public class Logs4adminServiceImpl implements Logs4adminService {
    @Resource
    private Logs4adminMapper logs4adminMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Logs4admin> selectAll() {
        return logs4adminMapper.selectAll();
    }

    @Override
    public void insert(Logs4admin logs4admin) {
        logs4adminMapper.insert(logs4admin);
    }
}
