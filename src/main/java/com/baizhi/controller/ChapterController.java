package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import com.baizhi.util.MyFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private AlbumService albumService;

    @RequestMapping("show")
    public Map<String,Object> show(Integer page,Integer rows){
        Map<String, Object> map = chapterService.showAll(page, rows);
        return map;
    }

    @RequestMapping("edit")
    public Map<String,Object> edit(MultipartFile file, Chapter chapter, HttpServletRequest request){
        System.out.println(chapter);
        System.out.println(file.getOriginalFilename()+file.getContentType()+file.getSize());
        Map<String, Object> edit = chapterService.edit(file, chapter, request);
        return edit;
    }

    @RequestMapping("getAudio")
    public void getImg(String audioPath, String type, HttpServletRequest request, HttpServletResponse response) throws IOException {
        MyFileUtils.fileDownload(audioPath,"/upload/chapter/",type,request,response);
        String id = audioPath.split("\\.")[0];
        Chapter chapter = new Chapter();
        chapter.setId(id);
        if("inline".equals(type)){
            chapter.setViewNum(1);
        }else{
            chapter.setDownloadNum(1);
        }
        chapterService.edit(null, chapter,null);
    }

    @RequestMapping("getAllAlbum")
    public List<Album> getAllAlbum(){
       return albumService.showAllAlbum();
    }

    @RequestMapping("delete")
    public Map<String, Object> delete(String fileName ,HttpServletRequest request){
        System.out.println(fileName);
        String[] split = fileName.split("\\.");
        for (String s : split) {
            System.out.println(s);
        }
        String id = split[0];
        System.out.println(id);
        MyFileUtils.fileDelete(fileName,"/upload/chapter/",request);
        Map<String, Object> delete = chapterService.delete(id);
        return delete;
    }

}
