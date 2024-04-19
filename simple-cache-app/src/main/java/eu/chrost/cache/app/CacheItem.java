package eu.chrost.cache.app;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
class CacheItem {
    @Id
    @Column(name = "k")
    private String key;
    @Column(name = "v")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    protected CacheItem() {}

    public CacheItem(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
