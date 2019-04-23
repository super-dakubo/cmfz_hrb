package com.baizhi.service;

import com.baizhi.entity.Album;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface AlbumService {
    Album selectOne(String id);
    Map<String,Object> showAll(Integer pageNow, Integer PageSize);
    Map<String,Object> edit(MultipartFile file, Album album, HttpServletRequest request);
    Map<String,Object> delete(String  id);
    List<Album> showAllAlbum();
}
