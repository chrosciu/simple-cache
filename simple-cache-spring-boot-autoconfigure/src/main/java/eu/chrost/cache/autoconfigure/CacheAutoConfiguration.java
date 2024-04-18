package eu.chrost.cache.autoconfigure;

import eu.chrost.cache.library.Cache;
import eu.chrost.cache.library.CacheConfig;
import eu.chrost.cache.library.CacheStorage;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnClass(Cache.class)
@EnableConfigurationProperties(CacheProperties.class)
class CacheAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public CacheStorage cacheStorage() {
        return new HashMapCacheStorage();
    }

    @Bean
    public Cache cache(CacheStorage cacheStorage, CacheProperties cacheProperties) {
        return new Cache(cacheStorage, new CacheConfig(cacheProperties.size()));
    }
}
