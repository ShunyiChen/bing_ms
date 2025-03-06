//package com.bing.file.config;
//
//import com.aliyun.oss.OSS;
//import com.aliyun.oss.OSSClientBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Minio 配置信息
// *
// * @author Simeon
// */
//@Configuration
//@ConfigurationProperties(prefix = "aliyun")
//public class AliyunOssConfig
//{
//
//    /**
//     * 服务地址
//     */
//    private String endpoint;
//
//    /**
//     * AccessKey ID
//     */
//    private String accessKeyId;
//
//    /**
//     * AccessKey Secret
//     */
//    private String accessKeySecret;
//
//    /**
//     * 存储桶名称
//     */
//    private String bucketName;
//
//    /**
//     * 存储桶访问域名
//     */
//    private String bucketDomain;
//
//    public String getEndpoint() {
//        return endpoint;
//    }
//
//    public void setEndpoint(String endpoint) {
//        this.endpoint = endpoint;
//    }
//
//    public String getAccessKeyId() {
//        return accessKeyId;
//    }
//
//    public void setAccessKeyId(String accessKeyId) {
//        this.accessKeyId = accessKeyId;
//    }
//
//    public String getAccessKeySecret() {
//        return accessKeySecret;
//    }
//
//    public void setAccessKeySecret(String accessKeySecret) {
//        this.accessKeySecret = accessKeySecret;
//    }
//
//    public String getBucketName() {
//        return bucketName;
//    }
//
//    public void setBucketName(String bucketName) {
//        this.bucketName = bucketName;
//    }
//
//    public String getBucketDomain() {
//        return bucketDomain;
//    }
//
//    public void setBucketDomain(String bucketDomain) {
//        this.bucketDomain = bucketDomain;
//    }
//
//    @Bean
//    public OSS getOssClient()
//    {
//        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//    }
//}
