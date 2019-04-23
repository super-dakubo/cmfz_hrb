package com.baizhi.service;

import com.baizhi.entity.Logs4admin;

import java.util.List;

public interface Logs4adminService {
    List<Logs4admin> selectAll();
    void insert(Logs4admin logs4admin);
}
