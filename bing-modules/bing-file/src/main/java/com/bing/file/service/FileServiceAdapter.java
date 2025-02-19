package com.bing.file.service;

import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.ListObjectsV2Result;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件服务接口适配器
 *
 * @author Simeon
 */
public abstract class FileServiceAdapter implements ISysFileService {

    public String uploadFile(MultipartFile file) throws Exception {
        return Strings.EMPTY;
    }

    public ListObjectsV2Result listFiles(String prefix) throws Exception {
        return new ListObjectsV2Result();
    }

    public DeleteObjectsResult deleteFiles(List<String> keys) throws Exception {
        return new DeleteObjectsResult();
    }

    public void createNewFolder(String folderPath) throws Exception {
    }

//    public Void downLoadFile(String url) {
//
//
//    }

}
