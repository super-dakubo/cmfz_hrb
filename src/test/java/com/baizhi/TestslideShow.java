package com.baizhi;

import com.baizhi.entity.Slideshow;
import com.baizhi.service.SlideshowService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestslideShow {

    @Autowired
    private SlideshowService slideshowService;

    @Test
    public void testshowAll(){
        List<Slideshow> slideshows = slideshowService.showAll(null, null);
        for (Slideshow slideshow : slideshows) {
            System.out.println(slideshow);
        }
    }
    @Test
    public void doimgPath(){
        List<Slideshow> slideshows = slideshowService.showAll(1,20);
        for (Slideshow slideshow : slideshows) {
            String name = slideshow.getTitle();
            String[] split = name.split("\\.");
            String title= split[0];
            String houzhui = "."+split[0];
            Slideshow slideshow1 = new Slideshow();
            slideshow1.setId(slideshow.getId());
            slideshow1.setTitle(title);
            slideshow1.setImgPath(slideshow.getId()+"."+houzhui);
            slideshowService.edit(null, slideshow1,null);
        }
    }
}
