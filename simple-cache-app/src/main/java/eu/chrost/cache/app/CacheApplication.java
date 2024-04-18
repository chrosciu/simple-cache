package eu.chrost.cache.app;

import eu.chrost.cache.library.Cache;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
class CacheApplication implements CommandLineRunner {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CacheApplication.class);
    private final Cache cache;

    CacheApplication(Cache cache) {
        this.cache = cache;
    }

    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("{}", cache.getOrComputeValue("M", () -> "Marcin"));
        log.info("{}", cache.getOrComputeValue("T", () -> "Tomasz"));
        log.info("{}", cache.getOrComputeValue("M", () -> "Marek"));
    }
}
