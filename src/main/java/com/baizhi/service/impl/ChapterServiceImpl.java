package com.baizhi.service.impl;

import com.baizhi.conf.Log4edit;
import com.baizhi.dao.ChapterMapper;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import com.baizhi.util.AudioUtil;
import com.baizhi.util.MyFileUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class ChapterServiceImpl implements ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> showAll(Integer page, Integer rows) {
        Map<String,Object> map = new HashMap<>();
        PageHelper.startPage(page,rows);
        List<Chapter> select = chapterMapper.select(new Chapter());
        PageInfo info = new PageInfo(select);
        map.put("rows",select);
        map.put("records",info.getTotal());
        map.put("total",info.getPages());
        map.put("page",info.getPageNum());
        return map;
    }

    @Override
    @Log4edit("修改章节信息或添加章节加音频上传")
    public Map<String, Object> edit(MultipartFile file, Chapter chapter, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        map.put("status",false);
        if(StringUtil.isEmpty(chapter.getId())){
            //添加
            try{
                //文件上传
                String id = UUID.randomUUID().toString();
                String extension = FilenameUtils.getExtension(file.getOriginalFilename());//获取文件名后缀 没有"."
                MyFileUtils.fileUpload(request,file,"/upload/chapter/",id+"."+extension);
                //数据处理
                long audioDuration = AudioUtil.getAudioLength("/upload/chapter/"+id+"."+extension,request);
                chapter.setId(id);
                chapter.setAudioDuration(audioDuration);
                chapter.setAudioSize(file.getSize());
                chapter.setAudioPath(id+"."+extension);
                chapter.setUploadDate(new Date());

                chapterMapper.insert(chapter);
            }catch (Exception e){
                map.put("message","添加文件失败");
                return map;
            }
        }else{
            //修改
            try {
                chapterMapper.update(chapter);
            }catch (Exception e){
                map.put("message","更新文件失败");
                return map;
            }
        }
        map.put("status",true);
        return map;
    }

    @Override
    @Log4edit("根据主键删除章节")
    public Map<String, Object> delete(String id) {
        Map<String,Object> map = new HashMap<>();
        map.put("status",false);
        try{
            int i = chapterMapper.deleteByPrimaryKey(id);
            if(i<1){
                throw new RuntimeException();
            }
        }catch (Exception e){
            map.put("message","删除失败");
            return map;
        }
        map.put("status",true);
        return map;
    }

    @Override
    public List<Chapter> select(Chapter chapter) {
        return chapterMapper.select(chapter);
    }
}
