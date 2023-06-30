package com.spring.cache.demo;

import java.util.concurrent.TimeUnit;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.CaffeineSpec;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@EnableCaching
public class CaffeineCacheConfig {

	@Bean
	public CacheManager cacheManager() {
		log.info("Starting CaffeineCacheManager");
		CaffeineCacheManager cacheManager = new CaffeineCacheManager("employee", "employees");
		cacheManager.setAllowNullValues(false);
		//cacheManager.setCaffeineSpec(caffeineSpec());
		cacheManager.setCaffeine(CaffeineCacheBuilder());
		return cacheManager;
		
	}
	
	private CaffeineSpec caffeineSpec(){
		return CaffeineSpec.parse("initialCapacity=100;maximumSize=500;expireAfterAccess=5m;weakKeys;recordStats;");
	}
	
	private Caffeine<Object, Object> CaffeineCacheBuilder(){
		return Caffeine.newBuilder()
				.initialCapacity(100)
				.maximumSize(500)
				.expireAfterAccess(5, TimeUnit.MINUTES)
				.weakKeys()
				.removalListener(new CustomRemovalListener())
				.recordStats();
	}
	
	class CustomRemovalListener implements RemovalListener<Object, Object>{
		@Override
		public void onRemoval(@Nullable Object key, @Nullable Object value, RemovalCause cause) {
			log.info("removal listener called with key " + key + " cause" +  cause.toString() + " " + cause.wasEvicted());
		}
	}
}
