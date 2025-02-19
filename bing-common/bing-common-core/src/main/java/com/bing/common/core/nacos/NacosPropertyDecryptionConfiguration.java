package com.bing.common.core.nacos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author Simeon
 */
@Configuration
public class NacosPropertyDecryptionConfiguration {

    @Bean
    public static NacosPropertyDecryptionBeanFactoryPostProcessor
        enableEncryptablePropertySourcesPostProcessor(final ConfigurableEnvironment environment) {
        return new NacosPropertyDecryptionBeanFactoryPostProcessor(environment);
    }
}
