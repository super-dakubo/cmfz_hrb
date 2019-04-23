package com.baizhi.service;

import com.baizhi.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ChapterService {
    Map<String,Object> showAll(Integer page,Integer rows);
    Map<String,Object> edit(MultipartFile file, Chapter chapter, HttpServletRequest request);
    Map<String,Object> delete(String id);
    List<Chapter> select(Chapter chapter);
}
