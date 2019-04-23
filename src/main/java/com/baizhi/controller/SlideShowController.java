package com.baizhi.controller;

import com.baizhi.entity.Slideshow;
import com.baizhi.service.SlideshowService;
import com.baizhi.util.MyFileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("slideshow")
public class SlideShowController {

    @Autowired
    private SlideshowService slideshowService;

    @RequestMapping("showAll")
    @ResponseBody
    public Map<String,Object> showAll(Integer page,Integer rows){
        Map<String,Object> map = new HashMap<>();
        List<Slideshow> slideshows = slideshowService.showAll(page, rows);
        map.put("page",page);
        map.put("rows",slideshows);
        Integer count = slideshowService.count();
        map.put("records", count);
        if(count%rows==0){
            map.put("total",count/rows);
        }else{
            map.put("total",count/rows+1);
        }
        return map;
    }


    @RequestMapping("edit")
    public Map<String, Object> edit(MultipartFile fff, Slideshow slideshow, HttpServletRequest request) throws IOException {
        Map<String, Object> edit = slideshowService.edit(fff, slideshow,request);
        return edit;

    }

    @RequestMapping("download")
    public void download(String imgPath, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //文件后缀
        String extension = FilenameUtils.getExtension(imgPath);
        //设置响应类型
        response.setContentType(request.getSession().getServletContext().getMimeType("."+extension));
        MyFileUtils.fileDownload(imgPath,"/upload/slideshow/","inline",request,response);

    }
    @RequestMapping("downloadxls")
    public void downloadxls(HttpServletResponse response){
        //创建 Excel 工作薄对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建工作表
        HSSFSheet sheet = workbook.createSheet("用户信息");
        //创建标题行
        HSSFRow row = sheet.createRow(0);
        String[] title = {"编号","标题","图片相对路径","图片描述","激活状态","上传日期"};
        //创建单元格对象
        HSSFCell cell = null;
        for (int i = 0; i < title.length; i++) {
            //i 标示列索引
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }
        //处理日期格式
        HSSFCellStyle cellStyle = workbook.createCellStyle(); //样式对象
        HSSFDataFormat dataFormat = workbook.createDataFormat(); //日期格式
        cellStyle.setDataFormat(dataFormat.getFormat("yyyy 年 MM 月 dd 日")); //设置日期格式
        //处理数据行
        List<Slideshow> slideshows = slideshowService.showAll(null, null);
        for (int i = 1; i < slideshows.size(); i++) {
            Slideshow slideshow = slideshows.get(i - 1);
            row = sheet.createRow(i);
            row.createCell(0).setCellValue(slideshow.getId());
            row.createCell(1).setCellValue(slideshow.getTitle());
            row.createCell(2).setCellValue(slideshow.getImgPath());
            row.createCell(3).setCellValue(slideshow.getDescribes());
            row.createCell(4).setCellValue(slideshow.getStatus());
            //设置出生年月格式
            cell = row.createCell(5);
            cell.setCellValue(slideshow.getUploadDate());
            cell.setCellStyle(cellStyle);
        }
        String fileName = "用户报表("+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+").xls";
        //处理中文下载名乱码
        try {
            fileName = new
                    String(fileName.getBytes("gbk"),"iso-8859-1");
        //设置 response
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-disposition","attachment;filename="+fileName);

            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @RequestMapping("xls2sql")
    public void upload(MultipartFile file) {
        try {
            // 获取本地 Excel 文件输入流，并创建工作薄对象
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            // 获取工作表
            HSSFSheet sheet = workbook.getSheetAt(0);
            // 声明行对象
            HSSFRow row = null;
            //声明集合
            List<Slideshow> list = new ArrayList<>();
            //注意：获取数据 需排除标题行 从数据行开始读取
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Slideshow slideshow = new Slideshow();
                // 获取当前工作表中的数据行信息 数据行索引从 1 开始
                row = sheet.getRow(i);
                // 打印结果
                slideshow.setId(row.getCell(0).getStringCellValue());
                slideshow.setTitle(row.getCell(1).getStringCellValue());
                slideshow.setImgPath(row.getCell(2).getStringCellValue());
                slideshow.setDescribes(row.getCell(3).getStringCellValue());
                slideshow.setStatus(row.getCell(4).getStringCellValue());
                slideshow.setUploadDate(row.getCell(5).getDateCellValue());
                list.add(slideshow);
            }
            slideshowService.insertList(list);
            System.out.println(list);
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
