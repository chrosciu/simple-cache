package eu.chrost.cache.app;

import org.springframework.data.jpa.repository.JpaRepository;

interface CacheItemRepository extends JpaRepository<CacheItem, String> {
}
