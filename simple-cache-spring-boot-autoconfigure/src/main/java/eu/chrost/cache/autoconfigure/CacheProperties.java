package eu.chrost.cache.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "eu.chrost.cache")
record CacheProperties(int size) {
    public CacheProperties {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be positive value");
        }
    }
}
