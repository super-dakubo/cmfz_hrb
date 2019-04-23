package com.baizhi.conf;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
// 在哪里加入注解
@Target({ElementType.METHOD})
public @interface Log4edit {
    String value();
}
