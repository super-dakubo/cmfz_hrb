package com.baizhi.service.impl;

import com.baizhi.dao.AdminMapper;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Override
    public Map<String,Object> login(Admin admin) {
        Map<String,Object> map = new HashMap<String, Object>();
        Admin admin2 = new Admin();
        admin2.setUsername(admin.getUsername());
        Admin admin1 = adminMapper.selectOne(admin2);
        if(admin1==null){
            map.put("statuscode","505");
            map.put("message","不存在该用户");
            return map;
        }
        if(!admin.getPassword().equals(admin1.getPassword())){
            map.put("statuscodr","504");
            map.put("message","密码输入错误");
            return map;
        }
        map.put("login",admin1);
        map.put("statuscodr","200");
        return map;
    }
}
