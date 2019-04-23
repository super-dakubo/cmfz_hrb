package com.baizhi.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("code")
public class CodeController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @RequestMapping("getImg")
    public void getImg(HttpSession session, HttpServletResponse response) throws IOException {
        String text = defaultKaptcha.createText();
        session.setAttribute("code",text);
        BufferedImage image = defaultKaptcha.createImage(text);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image,"png",outputStream);
    }
    @RequestMapping("checkCode")
    public Boolean checkCode(HttpSession session,String code){
        if(code.equals(session.getAttribute("code"))){
            return true;
        }
        return false;
    }
}
