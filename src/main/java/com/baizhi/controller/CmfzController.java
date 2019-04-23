package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Slideshow;
import com.baizhi.service.AlbumService;
import com.baizhi.service.SlideshowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("cmfz")
public class CmfzController {
    @Autowired
    private SlideshowService slideshowService;
    @Autowired
    private AlbumService albumService;

    @RequestMapping("first_page")
    public Map<String,List<Map<String,String>>> getFirstPage(String uid,String type){
        Map<String,List<Map<String,String>>> map = new HashMap<>();
        if("all".equals(type)){
            //header
            List<Map<String,String>> header = new ArrayList<>();
            List<Slideshow> slideshows = slideshowService.showAll(1, 5);
            for (Slideshow slideshow : slideshows) {
                Map<String,String> headerMap = new HashMap<>();
                headerMap.put("thumbnail",slideshow.getImgPath());
                headerMap.put("desc",slideshow.getDescribes());
                headerMap.put("id",slideshow.getId());
                header.add(headerMap);
            }
            //body
            List<Map<String,String>> body = new ArrayList<>();
            Map<String, Object> albummap = albumService.showAll(1, 6);
            List<Album> rows = (List<Album>) albummap.get("rows");
            for (Album album : rows) {
                System.out.println(album);
                Map<String,String> bodyMap = new HashMap<>();
                bodyMap.put("thunbnail",album.getImgPath());
                bodyMap.put("title",album.getName());
                bodyMap.put("author",album.getAuthor());
                bodyMap.put("type","0");
                if(album.getCounts()==null){
                    bodyMap.put("set_count","0");
                }else {
                    bodyMap.put("set_count", album.getCounts().toString());
                }
                bodyMap.put("create_date",album.getUploadDate().toString());
                body.add(bodyMap);
            }
            //artical
            List<Map<String,String>> artical = new ArrayList<>();


            //传数据
            map.put("header",header);
            map.put("body",body);
            //map.put("artical",artical);
        }else if("wen".equals(type)){
            //body
            List<Map<String,String>> body = new ArrayList<>();
            Map<String, Object> albummap = albumService.showAll(1, 6);
            List<Album> rows = (List<Album>) albummap.get("rows");
            for (Album album : rows) {
                Map<String,String> bodyMap = new HashMap<>();
                bodyMap.put("thunbnail",album.getImgPath());
                bodyMap.put("title",album.getName());
                bodyMap.put("author",album.getAuthor());
                bodyMap.put("type","0");
                if(album.getCounts()==null){
                    bodyMap.put("set_count","0");
                }else {
                    bodyMap.put("set_count", album.getCounts().toString());
                }
                bodyMap.put("create_date",album.getUploadDate().toString());
                body.add(bodyMap);
            }
            map.put("body",body);
        }else if ("si".equals(type)){

        }
        return map;
    }



}
