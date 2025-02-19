package com.bing.file.service;

import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.ListObjectsV2Result;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

/**
 * 文件服务接口
 * 
 * @author Simeon
 */
public interface ISysFileService
{
    /**
     * Filing文件上传接口
     * 
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    String handleFilingUpload(MultipartFile file) throws Exception;

    /**
     * 系统管理文件上传接口
     *
     * @param file 文件
     * @param directory 目录
     * @return
     * @throws Exception
     */
    String handleFilingUpload(MultipartFile file, String directory) throws Exception;

    /**
     * 获取文件列表
     *
     * @return
     * @throws Exception
     */
    ListObjectsV2Result listFiles(String prefix) throws Exception;

    /**
     * 删除指定名称的多个文件
     *
     * @param keys 文件相对路径(路径中不能包含Bucket名称。)
     * @return
     * @throws Exception
     */
    DeleteObjectsResult deleteFiles(List<String> keys) throws Exception;

    /**
     * 创建新文件夹
     *
     * @param folderPath 文件夹路径（BucketName + FolderName）
     * @throws Exception
     */
    void createNewFolder(String folderPath) throws Exception;

//    OutputStream downLoadFile(String url, HttpServletResponse response);


}
