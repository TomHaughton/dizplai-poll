package com.thomashaughton.dizplaipoll.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@EnableCaching
@Configuration
public class CacheConfig {

    public static final String POLL_CACHE = "pollCache";
    public static final String POLL_ANALYSIS_CACHE = "pollAnalysisCache";

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(List.of(
                new ConcurrentMapCache(POLL_CACHE),
                new ConcurrentMapCache(POLL_ANALYSIS_CACHE)
        ));
        return cacheManager;
    }

    @CacheEvict(allEntries = true, value = {POLL_ANALYSIS_CACHE})
    @Scheduled(fixedDelay = 5000)
    public void reportCacheEvict() {
        System.out.println("Poll Response Cache Evicted");
    }
}