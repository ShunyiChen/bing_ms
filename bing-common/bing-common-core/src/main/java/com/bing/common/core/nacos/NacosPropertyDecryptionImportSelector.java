package com.bing.common.core.nacos;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Simeon
 */
public class NacosPropertyDecryptionImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{NacosPropertyDecryptionConfiguration.class.getName()};
    }
}