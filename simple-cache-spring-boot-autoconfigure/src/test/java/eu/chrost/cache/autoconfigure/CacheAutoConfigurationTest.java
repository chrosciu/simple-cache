package eu.chrost.cache.autoconfigure;

import eu.chrost.cache.library.CacheStorage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class CacheAutoConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(CacheAutoConfiguration.class));

    @Test
    void givenDefaultConfigurationAndNoPropertySet_whenRunContext_thenExceptionIsThrown() {
        contextRunner.run(context -> {
            assertThatExceptionOfType(IllegalStateException.class)
                    .isThrownBy(() -> context.getBean(CacheStorage.class));
        });
    }

    @Test
    void givenDefaultConfiguration_whenGetCacheStorageBean_thenHashMapCacheStorageInstanceIsReturned() {
        contextRunner.withPropertyValues("eu.chrost.cache.size=5").run(context -> {
            assertThat(context).hasSingleBean(CacheStorage.class);
            assertThat(context.getBean(CacheStorage.class)).isSameAs(context.getBean(HashMapCacheStorage.class));
        });
    }

    @Test
    void givenUserConfiguration_whenGetCacheStorageBean_thenHashMapCacheStorageInstanceBacksOff() {
        contextRunner
                .withUserConfiguration(UserConfiguration.class)
                .withPropertyValues("eu.chrost.cache.size=5")
                .run(context -> {
            assertThat(context).hasSingleBean(CacheStorage.class);
            assertThatExceptionOfType(NoSuchBeanDefinitionException.class)
                    .isThrownBy(() -> context.getBean(HashMapCacheStorage.class));
        });
    }

    @Configuration
    static class UserConfiguration {
        @Bean
        CacheStorage mockedCacheStorage() {
            return Mockito.mock(CacheStorage.class);
        }

    }
}
