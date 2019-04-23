package com.baizhi;

import com.baizhi.dao.ProvinceMapper;
import com.baizhi.entity.Province;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestProvince {

    @Autowired
    private ProvinceMapper provinceMapper;

    @Test
    public void testlogin() throws JsonProcessingException {
        List<Province> provinces = provinceMapper.selectAll("男");
        System.out.println(provinces);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(provinces);
        System.out.println(s);
    }
}
