package com.baizhi.conf;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

//@Configuration
@EnableConfigurationProperties(value = JDBCProp.class)
public class JdbcConfig {
    @Autowired
    private  JDBCProp jdbcProp;

    @Bean
    public DruidDataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(jdbcProp.url);
        druidDataSource.setDriverClassName(jdbcProp.driver);
        druidDataSource.setUsername(jdbcProp.username);
        druidDataSource.setPassword(jdbcProp.password);
        return druidDataSource;
    }
}
