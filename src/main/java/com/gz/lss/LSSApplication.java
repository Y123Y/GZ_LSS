package com.gz.lss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@MapperScan("com.gz.lss.dao")
public class LSSApplication {

    public static void main(String[] args) {
        SpringApplication.run(LSSApplication.class, args);
    }

}
