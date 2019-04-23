package com.baizhi;

import com.baizhi.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUser {

    @Autowired
    private UserMapper userMapper;

//    @Test
//    public void testlogin(){
//
//        for (int i = 0; i < 20; i++) {
//            Integer integer = (int) (1000 + Math.random() * (9999 - 1000 + 1));
//            System.out.println(integer.toString());
//        }
//    }
    @Test
    public void testCount(){
        Integer integers = userMapper.countByDate(7);
            System.out.println(integers);
    }

}
