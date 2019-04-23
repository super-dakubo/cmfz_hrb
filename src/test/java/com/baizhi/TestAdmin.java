package com.baizhi;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAdmin {

    @Autowired
    private AdminService adminService;

    @Test
    public void testlogin(){
        Admin admin = new Admin();
        admin.setUsername("wq");
        admin.setPassword("123456");
        Map<String, Object> login = adminService.login(admin);
        System.out.println(login);
    }
}
