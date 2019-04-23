package com.baizhi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.baizhi.dao")
//@EnableScheduling//开启定时器任务
public class CmfzHrbApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmfzHrbApplication.class, args);
    }

}
