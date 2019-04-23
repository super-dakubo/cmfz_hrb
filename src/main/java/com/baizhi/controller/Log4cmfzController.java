package com.baizhi.controller;

import com.baizhi.entity.Logs4admin;
import com.baizhi.service.Logs4adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("log4cmfz")
public class Log4cmfzController {
    @Autowired
    private Logs4adminService logs4adminService;
    @RequestMapping("show")
    public List<Logs4admin> show(){
        return logs4adminService.selectAll();
    }
}
