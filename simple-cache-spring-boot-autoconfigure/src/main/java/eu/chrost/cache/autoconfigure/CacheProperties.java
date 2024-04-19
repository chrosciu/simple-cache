package eu.chrost.cache.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "eu.chrost.cache")
record CacheProperties(@DefaultValue(value = "10") int size) {
    public CacheProperties {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be positive value");
        }
    }
}
