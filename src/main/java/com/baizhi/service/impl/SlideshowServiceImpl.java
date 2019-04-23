package com.baizhi.service.impl;

import com.baizhi.conf.Log4edit;
import com.baizhi.dao.SlideshowMapper;
import com.baizhi.entity.Slideshow;
import com.baizhi.service.SlideshowService;
import com.baizhi.util.MyFileUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@Transactional
public class SlideshowServiceImpl implements SlideshowService {
    @Resource
    private SlideshowMapper slideshowMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Slideshow> showAll(Integer pageNow,Integer PageSize) {
        if(pageNow!=null&&PageSize!=null)
            PageHelper.startPage(pageNow,PageSize);
        List<Slideshow> slideshows = slideshowMapper.selectAll();
        return slideshows;
    }

    @Override
    @Log4edit("修改轮播图信息或添加轮播图")
    public Map<String, Object> edit(MultipartFile file, Slideshow slideshow, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        map.put("status",false);
        if(StringUtil.isEmpty(slideshow.getId())){
            //添加
            try {
                //文件上传
                String id = UUID.randomUUID().toString();
                String extension = FilenameUtils.getExtension(file.getOriginalFilename());//获取文件名后缀 没有"."
                MyFileUtils.fileUpload(request,file,"/upload/slideshow/",id+"."+extension);
                //整理数据
                slideshow.setId(id);
                slideshow.setTitle(slideshow.getTitle()+"."+extension);
                slideshow.setImgPath(id+"."+extension);
                slideshow.setUploadDate(new Date());
                //存入数据库
                int insert = slideshowMapper.insert(slideshow);
                if(insert==0){
                    throw new RuntimeException();
                }
            }catch (Exception e){
                map.put("message","添加失败！");
                return map;
            }

        }else {
            //修改
            int i = slideshowMapper.updateByPrimaryKeySelective(slideshow);
            if(i==0){
                map.put("message","更新失败！");
                return map;
            }
        }
        map.put("status",true);
        return map;
    }

    @Override
    @Log4edit("根据主键删除轮播图信息")
    public Map<String, Object> delete(String id) {
        int id1 = slideshowMapper.deleteByPrimaryKey("id");
        return null;
    }

    @Override
    public Integer count() {
        return slideshowMapper.selectCount(null);
    }

    @Override
    @Log4edit("批量添加轮播图信息")
    public void insertList(List<Slideshow> list) {
        slideshowMapper.insertList(list);
    }
}
