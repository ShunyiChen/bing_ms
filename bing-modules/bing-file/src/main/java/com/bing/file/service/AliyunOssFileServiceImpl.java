package com.bing.file.service;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.*;
import com.bing.file.config.AliyunOssConfig;
import com.bing.file.utils.MultipartFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 阿里云OSS文件服务接口的实现
 *
 * @author Simeon
 */
@Primary
@Service
public class AliyunOssFileServiceImpl extends FileServiceAdapter {
    private static final Logger log = LoggerFactory.getLogger(AliyunOssFileServiceImpl.class);
    private final AliyunOssConfig aliyunOssConfig;
    private final OSS ossClient;
//    private static final String LEGALDB_BASE_DIR = "legaldatabase/";
//    private static final String LEGALDB_TEMPLATE_DIR = LEGALDB_BASE_DIR + "templates/";
    private static final String FILING_BASE_DIR = "filingsystem/";
    private static final String FILING_BOX_BASE_DIR = FILING_BASE_DIR + "box/";

    public AliyunOssFileServiceImpl(AliyunOssConfig aliyunOssConfig, OSS ossClient) {
        this.aliyunOssConfig = aliyunOssConfig;
        this.ossClient = ossClient;
    }

    @Override
    public String handleFilingUpload(MultipartFile file) throws Exception {
        String folderName = MultipartFileUtils.createDirectoryWithDate(FILING_BOX_BASE_DIR);
        createNewFolder(folderName);

        String fileName = MultipartFileUtils.getFileNameWithTimestamp(file);
        PutObjectRequest putObjectRequest = new PutObjectRequest(
                aliyunOssConfig.getBucketName(),
                folderName + fileName,
                file.getInputStream());
        ossClient.putObject(putObjectRequest);
        String fullUrl = generateResignedUrl(folderName + fileName);
        //截取文件夹路径
        return fullUrl.substring(fullUrl.indexOf("/box/") + 5);
    }

    @Override
    public String handleFilingUpload(MultipartFile file, String directory) throws Exception {
        String folderName = MultipartFileUtils.createDirectoryWithDate(directory);
        createNewFolder(folderName);

        String fileName = MultipartFileUtils.getFileNameWithTimestamp(file);
        PutObjectRequest putObjectRequest = new PutObjectRequest(
                aliyunOssConfig.getBucketName(),
                folderName + fileName,
                file.getInputStream());
        ossClient.putObject(putObjectRequest);
        return generateResignedUrl(folderName + fileName); // 生成并返回签名链接
    }

    private String generateResignedUrl(String objectKey) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2099, Calendar.DECEMBER, 31, 23, 59, 59);
        Date expiration = calendar.getTime();

        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(
                aliyunOssConfig.getBucketName(),
                objectKey,
                HttpMethod.GET);
        request.setExpiration(expiration);

        URL url = ossClient.generatePresignedUrl(request);
        return url.toString(); // 返回最终的URL
    }

    @Override
    public ListObjectsV2Result listFiles(String prefix) throws Exception {
        return ossClient.listObjectsV2(aliyunOssConfig.getBucketName(), prefix);
    }

    @Override
    public DeleteObjectsResult deleteFiles(List<String> keys) throws Exception {
        /*
         * Delete all objects uploaded recently under the bucket
         */
        log.info("\nDeleting all objects:");
        return ossClient.deleteObjects(
                new DeleteObjectsRequest(aliyunOssConfig.getBucketName()).withKeys(keys));
    }

    @Override
    public void createNewFolder(String folderPath) throws Exception {
        /*
         * Checks if the folder is existed, otherwise create a new one
         */
        log.info("\nCreate a new folder");
        /*
         * Create an empty folder without request body, note that the key must be
         * suffixed with a slash
         */
        if(!ossClient.doesObjectExist(aliyunOssConfig.getBucketName(), folderPath)) {
            ossClient.putObject(aliyunOssConfig.getBucketName(), folderPath, new ByteArrayInputStream(new byte[0]));
        }
    }

    public static void main(String[] args) {
        String s = "https://cri-riqnfw19m2zc7zaf-registry.oss-cn-shanghai.aliyuncs.com/filingsystem/box/2025-01-16/2025-01-14 21_33_57 Box Profile_20250116014248.xlsx";
        System.out.println(s.substring(s.indexOf("/box/") + 5));


    }
}
