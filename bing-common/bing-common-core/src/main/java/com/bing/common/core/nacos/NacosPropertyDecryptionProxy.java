package com.bing.common.core.nacos;

import com.bing.common.core.utils.AESUtils;
import org.springframework.core.env.EnumerablePropertySource;

/**
 * NacosPropertyDecryptionProxy类用于代理Nacos配置文件中的属性值解密操作。
 *
 * 该类提供了解密Nacos配置文件中加密属性值的方法，通过在应用程序中使用此代理类，
 * 可以确保配置文件中的敏感信息以加密形式存储，并在读取时自动解密。
 *
 * 主要功能包括：
 * - 连接Nacos配置中心
 * - 获取加密的配置属性
 * - 解密属性值并返回明文
 *
 * 使用方法：
 * 1. 在应用程序配置类上添加 @EnableNacosPropertyDecryption 注解。
 * 2. 在需要获取配置属性的地方，使用该代理类的方法获取解密后的属性值。
 *
 * 注意事项：
 * - 确保正确配置Nacos连接信息。
 * - 加密和解密算法需与配置中心保持一致。
 *
 * @author Simeon
 */
public class NacosPropertyDecryptionProxy extends EnumerablePropertySource<Object> {

    private static final String ENCRYPT_VALUE_PREFIX = "cipher(";
    private static final String ENCRYPT_VALUE_SUFFIX = ")";

    private EnumerablePropertySource delegate;

    public NacosPropertyDecryptionProxy(EnumerablePropertySource delegate) {
        super(delegate.getName(), delegate.getSource());
        this.delegate = delegate;
    }

    @Override
    public String[] getPropertyNames() {
        return delegate.getPropertyNames();
    }

    @Override
    public Object getProperty(String name) {
        Object value = delegate.getProperty(name);
        if (value instanceof String) {
            String trim = String.valueOf(value).trim();
            if (trim.startsWith(ENCRYPT_VALUE_PREFIX) && trim.endsWith(ENCRYPT_VALUE_SUFFIX)) {
                return AESUtils.complexAESDecrypt(trim.substring(ENCRYPT_VALUE_PREFIX.length(), trim.indexOf(ENCRYPT_VALUE_SUFFIX)));
            }
        }
        return value;
    }
}
