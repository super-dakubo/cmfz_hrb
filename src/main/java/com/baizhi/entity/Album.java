package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Table(name = "album")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    @Id
    private String id;

    private String name;

    private Integer mark;

    private String author;

    private String announcer;

    private Integer counts;

    private String abstracts;

    private String imgPath;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date uploadDate;

    private String status;

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        if(counts==null){
            this.counts = 0;
        }else {
            this.counts = counts;
        }
    }
}