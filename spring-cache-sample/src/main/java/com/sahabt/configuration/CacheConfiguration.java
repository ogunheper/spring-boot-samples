package com.sahabt.configuration;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfiguration {

    @Bean
    public CacheManager guavaCacheManager() {
        final ConcurrentMapCacheManager guavaCacheManager = new ConcurrentMapCacheManager() {
            @Override
            protected Cache createConcurrentMapCache(final String name) {
                return new ConcurrentMapCache(name,
                        CacheBuilder.newBuilder()
                                .maximumSize(5)
                                .expireAfterWrite(10, TimeUnit.SECONDS)
                                .build().asMap(), false);
            }
        };
        return guavaCacheManager;
    }

    // @Bean
    public CacheManager ehCacheManager() {
        net.sf.ehcache.config.CacheConfiguration booksCacheConfiguration = new net.sf.ehcache.config.CacheConfiguration();
        booksCacheConfiguration.name("bookCache").maxEntriesLocalHeap(10).copyOnRead(true);

        net.sf.ehcache.config.Configuration ehCacheConfiguration = new net.sf.ehcache.config.Configuration();
        ehCacheConfiguration.addCache(booksCacheConfiguration);

        net.sf.ehcache.CacheManager ehCacheCacheManager = net.sf.ehcache.CacheManager.newInstance(ehCacheConfiguration);

        return new EhCacheCacheManager(ehCacheCacheManager);
    }
}
