package eu.chrost.cache.autoconfigure;

import eu.chrost.cache.library.CacheStorage;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

class HashMapCacheStorage implements CacheStorage {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(HashMapCacheStorage.class);

    private final Map<String, String> map = new HashMap<>();

    @Override
    public String get(String key) {
        var value = map.get(key);
        log.info("get({}) => {}", key, value);
        return value;
    }

    @Override
    public void put(String key, String value) {
        log.info("put({}, {})", key, value);
        map.put(key, value);
    }

    @Override
    public void remove(String key) {
        log.info("remove({})", key);
        map.remove(key);
    }
}
