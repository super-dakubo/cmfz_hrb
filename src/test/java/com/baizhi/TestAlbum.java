package com.baizhi;

import com.baizhi.dao.AlbumMapper;
import com.baizhi.service.AlbumService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAlbum {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private AlbumMapper albumMapper;

    @Test
    public void testlogin(){
        //Map<String,Object> albums = albumService.showAll(1, 3);
//        PageHelper.startPage(2,3);
//        List<Album> albums = albumMapper.selectAll();
//        PageInfo info = new PageInfo(albums);
//
//        System.out.println(info);
    }

}
