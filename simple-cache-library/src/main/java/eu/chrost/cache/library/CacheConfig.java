package eu.chrost.cache.library;

public record CacheConfig(int size) {
    public CacheConfig {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be positive value");
        }
    }
}
