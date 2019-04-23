package com.baizhi.service.impl;

import com.baizhi.conf.Log4edit;
import com.baizhi.dao.AlbumMapper;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
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
public class AlbumServiceImpl implements AlbumService {
    @Resource
    private AlbumMapper albumMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String,Object> showAll(Integer pageNow, Integer PageSize) {
        Map<String,Object> map = new HashMap<String, Object>();
        PageHelper.startPage(pageNow,PageSize);
        List<Album> albums = albumMapper.showAll();
        PageInfo info = new PageInfo(albums);
        map.put("rows",albums);
        map.put("records",info.getTotal());
        map.put("total",info.getPages());
        map.put("page",info.getPageNum());
        return map;
    }

    @Override
    @Log4edit("专辑信息修改及图片上传")
    public Map<String, Object> edit(MultipartFile file, Album album, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        map.put("status",false);
        System.out.println(album);
        if(StringUtil.isEmpty(album.getId())){
            //这是添加操作
            try{
                //String realPath = request.getSession().getServletContext().getRealPath("/upload/album/");
                String id = UUID.randomUUID().toString();
                String extension = FilenameUtils.getExtension(file.getOriginalFilename());//获取文件名后缀 没有"."
                MyFileUtils.fileUpload(request,file,"/upload/album/",id+"."+extension);
                //整理数据
                album.setId(id);
                album.setCounts(0);
                album.setImgPath(id+"."+extension);
                album.setUploadDate(new Date());

                int i = albumMapper.insertSelective(album);
                System.out.println(i);
                if(i<=0){
                    throw new RuntimeException();
                }
            }catch (Exception e){
                map.put("message","添加失败！");
                return map;
            }
        }else {
            //修改
            int i = albumMapper.updateByPrimaryKeySelective(album);
            if(i<=0){
                map.put("message","更新失败！");
                return map;
            }
        }
        map.put("status",true);
        return map;
    }

    @Override
    @Log4edit("专辑信息冻结")
    public Map<String, Object> delete(String id) {
        return null;
    }

    @Override
    public List<Album> showAllAlbum() {
        return albumMapper.showAll();
    }

    @Override
    public Album selectOne(String id) {
        return albumMapper.selectByPrimaryKey(id);
    }
}
