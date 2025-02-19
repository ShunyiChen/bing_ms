package com.bing.common.core.nacos;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 * @author Simeon
 */
public class NacosPropertyDecryptionBeanFactoryPostProcessor implements BeanFactoryPostProcessor, Ordered {

    private static final String BOOTSTRAP_PROPERTY_SOURCES_NAME = "bootstrapProperties-";

    private final ConfigurableEnvironment environment;

    public NacosPropertyDecryptionBeanFactoryPostProcessor(ConfigurableEnvironment environment) {
        this.environment = environment;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        MutablePropertySources propertySources = environment.getPropertySources();
        for (PropertySource<?> propertySource : propertySources) {
            if (!isEncryptableConfig(propertySource)) {
                continue;
            }
            replaceApplicationConfig(propertySource, propertySources);
        }
    }

    private boolean isNacosConfig(PropertySource<?> propertySource) {
        String propertySourceName = propertySource.getName();
        return propertySourceName.startsWith(BOOTSTRAP_PROPERTY_SOURCES_NAME);
    }

    private boolean isEncryptableConfig(PropertySource<?> propertySource) {
        return isNacosConfig(propertySource);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 100;
    }

    private void replaceApplicationConfig(PropertySource<?> propertySource, MutablePropertySources propertySources) {
        propertySources.replace(propertySource.getName(), new NacosPropertyDecryptionProxy((EnumerablePropertySource) propertySource));
    }
}
