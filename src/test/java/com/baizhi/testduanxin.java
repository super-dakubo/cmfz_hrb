package com.baizhi;

import com.baizhi.util.DuanxinUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testduanxin {
    @Autowired
    private HttpSession session;

    @Test
    public void sendtext(){
        DuanxinUtil.sendMessage("15037921292",session);
        System.out.println(session);
        System.out.println(session.getAttribute("phoneCode"));
    }
    @Test
    public  void test(){
        System.out.println(session.getAttribute("phoneCode"));
    }
}
