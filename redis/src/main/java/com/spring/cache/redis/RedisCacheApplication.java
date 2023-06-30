package com.spring.cache.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableCaching
public class RedisCacheApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(RedisCacheApplication.class, args);
    }
}
