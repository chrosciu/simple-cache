package eu.chrost.cache.library;

public interface CacheStorage {
    String get(String key);

    void put(String key, String value);

    void remove(String key);
}
