package com.baizhi;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testJsoup {

    @Test
    public void test() throws IOException {
        Connection connect = Jsoup.connect("http://www.xbiquge.la/10/10489/");
        Document document = connect.get();
        //System.out.println(document.body());
        Elements href = document.getElementsByAttribute("href");
        //System.out.println(href);//获取所有a标签
        for (Element element : href) {
            System.out.println(element.ownText());
            System.out.println(element.attr("href"));
        }
    }
}
