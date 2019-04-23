package com.baizhi;

import com.baizhi.dao.SlideshowMapper;
import com.baizhi.entity.Slideshow;
import com.baizhi.service.SlideshowService;
import org.apache.poi.hssf.usermodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testPoi {
    @Autowired
    private SlideshowService slideshowService;
    @Resource
    private SlideshowMapper slideshowMapper;
    @Test
    public void testdaochu(){
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
        try {
            workbook.write(new File("e:\\用户.xls"));
            workbook.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void upload(){
        try {
            // 获取本地 Excel 文件输入流，并创建工作薄对象
            HSSFWorkbook workbook = new HSSFWorkbook(new
                    FileInputStream("e:\\用户.xls"));
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
            slideshowMapper.insertList(list);
            System.out.println(list);
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
