package com.baizhi.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jdbc")
public class JDBCProp {
    String url;
    String driver;
    String username;
    String password;
}
