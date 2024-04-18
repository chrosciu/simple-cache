package eu.chrost.cache.library;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class Cache {
    private final CacheStorage cacheStorage;
    private final CacheConfig cacheConfig;
    private final LinkedHashSet<Object> keys;

    public Cache(CacheStorage cacheStorage, CacheConfig cacheConfig) {
        this.cacheStorage = cacheStorage;
        this.cacheConfig = cacheConfig;
        this.keys = new LinkedHashSet<>(cacheConfig.size());
    }

    public Object getOrComputeValue(Object key, Supplier<Object> valueSupplier) {
        if (keys.contains(key)) {
            return cacheStorage.get(key);
        }
        Object value = valueSupplier.get();
        if (keys.size() >= cacheConfig.size()) {
            Object removedKey = keys.removeFirst();
            cacheStorage.remove(removedKey);
        }
        keys.addLast(key);
        cacheStorage.put(key, value);
        return value;
    }
}
