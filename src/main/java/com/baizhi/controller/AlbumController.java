package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import com.baizhi.util.MyFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;
    @RequestMapping("show")
    public Map<String,Object> show(Integer page,Integer rows){
        Map<String, Object> map = albumService.showAll(page, rows);
        return map;
    }

    @RequestMapping("edit")
    public Map<String, Object> edit(MultipartFile fff, Album album, HttpServletRequest request) throws IOException {
        Map<String, Object> edit = albumService.edit(fff, album,request);
        return edit;
    }

    @RequestMapping("download")
    public void download(String imgPath, HttpServletRequest request, HttpServletResponse response) throws IOException {

        MyFileUtils.fileDownload(imgPath,"/upload/album/","inline",request,response);

//        //文件后缀
//        String extension = FilenameUtils.getExtension(imgPath);
//        //设置响应类型
//        response.setContentType(request.getSession().getServletContext().getMimeType("."+extension));
//
//        //根据指定文件名去服务器指定目录获取文件
//        String realPath = request.getSession().getServletContext().getRealPath("/upload/album/");
//        FileInputStream is = new FileInputStream(new File(realPath, imgPath));
//        ServletOutputStream os = response.getOutputStream();
//        //设置文件打开方式  attachment 附件下载  inline 在线打开
//        response.setHeader("content-disposition","inline;");
//        IOUtils.copy(is,os);
//        IOUtils.closeQuietly(is);
//        IOUtils.closeQuietly(os);
    }

}
