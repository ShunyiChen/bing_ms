//package com.ey.cbsapp.filingsystem.service;
//
//import java.io.InputStream;
//
//import com.ey.cbsapp.filingsystem.config.MinioConfig;
//import com.ey.cbsapp.filingsystem.utils.FileUploadUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import com.alibaba.nacos.common.utils.IoUtils;
//import io.minio.MinioClient;
//import io.minio.PutObjectArgs;
//
//import jakarta.servlet.http.HttpServletResponse;
//
///**
// * Minio 文件存储
// *
// * @author Simeon
// */
//@Service
//public class MinioSysFileServiceImpl extends FileServiceAdapter
//{
//    @Autowired
//    private MinioConfig minioConfig;
//
//    @Autowired
//    private MinioClient client;
//
//    /**
//     * Minio文件上传接口
//     *
//     * @param file 上传的文件
//     * @return 访问地址
//     * @throws Exception
//     */
//    @Override
//    public String uploadFile(MultipartFile file) throws Exception
//    {
//        String fileName = FileUploadUtils.extractFilename(file);
//        InputStream inputStream = file.getInputStream();
//        PutObjectArgs args = PutObjectArgs.builder()
//                .bucket(minioConfig.getBucketName())
//                .object(fileName)
//                .stream(inputStream, file.getSize(), -1)
//                .contentType(file.getContentType())
//                .build();
//        client.putObject(args);
//        IoUtils.closeQuietly(inputStream);
//        return minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/" + fileName;
//    }
//
//
//
//
//}
