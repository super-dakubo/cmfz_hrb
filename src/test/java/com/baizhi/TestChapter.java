package com.baizhi;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestChapter {

    @Resource
    private ChapterService chapterService;

    @Test
    public void testSelect(){
        Chapter chapter = new Chapter();
        Album album = new Album();
        //album.setName("当当");
        chapter.setAlbum(album);
//        chapter.setId("1");
        List<Chapter> chapters = chapterService.select(chapter);
        chapters.forEach(chapter1 -> System.out.println("===>"+chapter1));
    }

    @Test
    public void testinsert(){
        Album album = new Album();
        album.setId("236a863f-822e-49ae-b60b-2b5d9747ab00");
        //chapterMapper.insert(new Chapter("7","赶路一","aa.mp3",1524l,360000l,new Date(),0,0,1, album));
    }

}
