package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("detail")
public class DetailController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ChapterService chapterService;
    @RequestMapping("wen")
    public Map<String,Object> getWen(String id,String uid){
        Map<String,Object> map = new HashMap<>();
        Album album = albumService.selectOne(id);
        Map<String,Object> introduction = new HashMap<>();
        introduction.put("thumbnail",album.getImgPath());
        introduction.put("title",album.getName());
        introduction.put("csore",album.getMark());
        introduction.put("author",album.getAuthor());
        introduction.put("broadcast",album.getAnnouncer());
        introduction.put("set_count",album.getCounts());
        introduction.put("brief",album.getAbstracts());
        introduction.put("create_date",album.getUploadDate());
        map.put("introduction",introduction);
        Chapter chapter = new Chapter();
        Album album1 = new Album();
        album1.setId(id);
        chapter.setAlbum(album1);
        List<Chapter> chapters = chapterService.select(chapter);
        map.put("list",chapters);
        return map;
    }
}
