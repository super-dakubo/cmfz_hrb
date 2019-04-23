package com.baizhi.service;

import com.baizhi.entity.Slideshow;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface SlideshowService {
    List<Slideshow> showAll(Integer pageNow,Integer PageSize);
    Map<String,Object> edit(MultipartFile file, Slideshow slideshow, HttpServletRequest request);
    Map<String,Object> delete(String  id);
    Integer count();
    void insertList(@Param("list") List<Slideshow> list);
}
