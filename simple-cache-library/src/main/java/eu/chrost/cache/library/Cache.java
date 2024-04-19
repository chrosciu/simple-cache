package eu.chrost.cache.library;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class Cache {
    private final CacheStorage cacheStorage;
    private final CacheConfig cacheConfig;
    private final LinkedHashSet<String> keys;

    public Cache(CacheStorage cacheStorage, CacheConfig cacheConfig) {
        this.cacheStorage = cacheStorage;
        this.cacheConfig = cacheConfig;
        this.keys = new LinkedHashSet<>(cacheConfig.size());
    }

    public String getOrComputeValue(String key, Supplier<String> valueSupplier) {
        if (keys.contains(key)) {
            return cacheStorage.get(key);
        }
        String value = valueSupplier.get();
        if (keys.size() >= cacheConfig.size()) {
            String removedKey = keys.removeFirst();
            cacheStorage.remove(removedKey);
        }
        keys.addLast(key);
        cacheStorage.put(key, value);
        return value;
    }
}
