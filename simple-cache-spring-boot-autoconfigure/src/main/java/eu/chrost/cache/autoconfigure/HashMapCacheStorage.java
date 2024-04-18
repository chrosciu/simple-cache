package eu.chrost.cache.autoconfigure;

import eu.chrost.cache.library.CacheStorage;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

class HashMapCacheStorage implements CacheStorage {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(HashMapCacheStorage.class);

    private final Map<Object, Object> map = new HashMap<>();

    @Override
    public Object get(Object key) {
        log.info("get({})", key);
        return map.get(key);
    }

    @Override
    public void put(Object key, Object value) {
        log.info("put({}, {})", key, value);
        map.put(key, value);
    }

    @Override
    public void remove(Object key) {
        log.info("remove({})", key);
        map.remove(key);
    }
}
