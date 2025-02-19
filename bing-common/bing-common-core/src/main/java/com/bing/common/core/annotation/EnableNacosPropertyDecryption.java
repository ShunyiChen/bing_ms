package com.bing.common.core.annotation;

import com.bing.common.core.nacos.NacosPropertyDecryptionImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Simeon
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(NacosPropertyDecryptionImportSelector.class)
public @interface EnableNacosPropertyDecryption {
}
