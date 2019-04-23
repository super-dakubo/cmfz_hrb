package com.baizhi;

import com.baizhi.service.UserService;
import com.baizhi.util.GoeasyUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGoeasy {

    @Autowired
    private UserService userService;

    @Test
    public void testlogin() throws JsonProcessingException {
        Map<String,String> map = new HashMap<>();
        map.put("aa","aa");
        map.put("bb","vv");

        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        ObjectMapper objectMapper = new ObjectMapper();
        String maps = objectMapper.writeValueAsString(map);
        String lists = objectMapper.writeValueAsString(list);
        System.out.println(maps);
        System.out.println(lists);

    }

    @Test
    public void testgoeasy(){
        GoeasyUtil.sendMessage("wjgsb");
    }

}
