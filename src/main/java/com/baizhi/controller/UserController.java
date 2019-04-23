package com.baizhi.controller;

import com.baizhi.entity.Province;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;




    @RequestMapping("map")
    public Map<String, List<Province>> getmap(){
        return userService.selectAllProvince();
    }
    @RequestMapping("bar")
    public List<Integer> getBar(){
        return userService.countBydate();
    }
}
