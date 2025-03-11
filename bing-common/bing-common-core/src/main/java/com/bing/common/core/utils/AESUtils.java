package com.bing.common.core.utils;

import cn.hutool.crypto.symmetric.AES;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class AESUtils {
    private static final String MODE = "CBC";
    private static final String PADDING = "PKCS7Padding";
    private static String iv = "09efd2776381bd87";
    private static String key = "b6fb6933bef669fd2dd8dc59fb8c5494";

    public static String complexAESEncrypt(String txt) {
        AES aes = new AES(MODE, PADDING,
                // 密钥，可以自定义
                key.getBytes(),
                // iv加盐，按照实际需求添加
                iv.getBytes());
        return aes.encryptHex(txt);
    }

    public static String complexAESDecrypt(String txt) {
        AES aes = new AES(MODE, PADDING,
                // 密钥，可以自定义
                key.getBytes(),
                // iv加盐，按照实际需求添加
                iv.getBytes());
        return aes.decryptStr(txt, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        System.out.println(complexAESEncrypt("r-uf6v74xvpal7w1luuv"));
//        System.out.println(complexAESEncrypt("Admin123"));
    }
}
