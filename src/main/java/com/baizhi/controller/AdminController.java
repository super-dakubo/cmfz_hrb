package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("login")
    public Map<String,Object> login(Admin admin, HttpSession session){
        Map<String, Object> map = adminService.login(admin);
        session.setAttribute("login",map.get("login"));
        map.remove("login");
        return map;
    }
    @RequestMapping("safeOut")
    public Boolean safeOut(HttpSession session){
        session.removeAttribute("login");
        return true;
    }
}
