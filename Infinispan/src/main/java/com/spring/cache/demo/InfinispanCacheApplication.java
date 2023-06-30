package com.spring.cache.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class InfinispanCacheApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(InfinispanCacheApplication.class, args);
    }
}
