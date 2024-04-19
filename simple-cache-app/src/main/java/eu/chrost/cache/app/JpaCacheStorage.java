package eu.chrost.cache.app;

import eu.chrost.cache.library.CacheStorage;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
class JpaCacheStorage implements CacheStorage {
    private final CacheItemRepository cacheItemRepository;

    JpaCacheStorage(CacheItemRepository cacheItemRepository) {
        this.cacheItemRepository = cacheItemRepository;
    }

    @Override
    public String get(String key) {
        return cacheItemRepository.findById(key).map(CacheItem::getValue).orElse(null);
    }

    @Transactional
    @Override
    public void put(String key, String value) {
        cacheItemRepository.findById(key).ifPresentOrElse(
                cacheItem -> cacheItem.setValue(value),
                () -> cacheItemRepository.save(new CacheItem(key, value))
        );
    }

    @Transactional
    @Override
    public void remove(String key) {
        cacheItemRepository.deleteById(key);
    }
}
