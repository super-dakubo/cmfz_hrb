package com.baizhi.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

public class MyFileUtils {
    /**
     * 文件上传
     * @param file 文件对象
     * @param path 文件存放位置的相对路径
     * @param newName 文件名
     * @throws IOException
     */
    public static void fileUpload(HttpServletRequest request, MultipartFile file,String path,String newName) throws IOException {
        String realPath = request.getSession().getServletContext().getRealPath(path);
        file.transferTo(new File(realPath,newName));
    }

    /**
     * 文件下载
     * @param fileName 文件名
     * @param dirPath 文件存放位置的相对路径
     * @param type  设置文件打开方式  attachment 附件下载  inline 在线打开
     * @throws IOException
     */
    public static void fileDownload(String fileName,String dirPath, String type ,HttpServletRequest request, HttpServletResponse response) throws IOException {
        String extension = FilenameUtils.getExtension(fileName);
        //设置响应类型
        response.setContentType(request.getSession().getServletContext().getMimeType("."+extension));

        //根据指定文件名去服务器指定目录获取文件
        String realPath = request.getSession().getServletContext().getRealPath(dirPath);
        File file = new File(realPath, fileName);
        FileInputStream is = new FileInputStream(file);
        ServletOutputStream os = response.getOutputStream();
        //设置文件打开方式  attachment 附件下载  inline 在线打开
        response.setHeader("content-disposition",type+";fileName="+ URLEncoder.encode(fileName,"UTF-8"));
        IOUtils.copy(is,os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
    }

    /**
     *  删除文件
     * @param fileName 文件名
     * @param dirPath 文件所在目录
     * @param request
     */
    public static void fileDelete(String fileName,String dirPath ,HttpServletRequest request){
        //根据指定文件名去服务器指定目录获取文件
        String realPath = request.getSession().getServletContext().getRealPath(dirPath);
        File file = new File(realPath, fileName);
        file.delete();
    }
}
