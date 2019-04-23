package com.baizhi.controller;

import com.baizhi.util.DuanxinUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("identify")
public class IdentifyController {

    @RequestMapping("obtain")
    public void obtain(String phone, HttpSession session){
        DuanxinUtil.sendMessage(phone,session);
    }
    @RequestMapping("check")
    public Map<String,String> check(String phone,String code,HttpSession session){
        Map<String,String> map = new HashMap<>();
        if(code.equals(session.getAttribute("phoneCode"))){
            map.put("result","success");
        }else {
            map.put("result","fail");
        }
        session.removeAttribute("phoneCode");
        return map;
    }
}
