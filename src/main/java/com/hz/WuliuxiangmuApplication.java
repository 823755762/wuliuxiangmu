package com.hz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.hz.mapper")
@EnableTransactionManagement(proxyTargetClass = true)
public class WuliuxiangmuApplication {

    public static void main(String[] args) {
        SpringApplication.run(WuliuxiangmuApplication.class, args);
    }

}
