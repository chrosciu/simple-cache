package eu.chrost.cache.library;

public interface CacheStorage {
    Object get(Object key);

    void put(Object key, Object value);

    void remove(Object key);
}
