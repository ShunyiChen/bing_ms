package com.bing.file.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.ListObjectsV2Result;
import com.aliyun.oss.model.OSSObjectSummary;
import com.bing.file.config.AliyunOssConfig;
import com.bing.file.service.ISysFileService;
import com.bing.common.core.domain.R;
import com.bing.common.core.utils.file.FileUtils;
import com.bing.system.api.domain.SysFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件请求处理
 *
 * @author Simeon
 */
@RestController
public class SysFileController {
    private static final Logger log = LoggerFactory.getLogger(SysFileController.class);

    @Autowired
    private ISysFileService sysFileService;

    private final AliyunOssConfig aliyunOssConfig;

    private final OSS ossClient;

    public SysFileController(AliyunOssConfig aliyunOssConfig, OSS ossClient) {
        this.aliyunOssConfig = aliyunOssConfig;
        this.ossClient = ossClient;
    }

    /**
     * 文件上传请求
     */
    @PostMapping("/upload/filing")
    public R<SysFile> filingUpload(MultipartFile file) {
        try {
            // 上传并返回访问地址
            String url = sysFileService.handleFilingUpload(file);
            SysFile sysFile = new SysFile();
            sysFile.setName(FileUtils.getName(file.getOriginalFilename()));
            sysFile.setUrl(url);
            return R.ok(sysFile);
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return R.fail(e.getMessage());
        }
    }

    /**
     * 系统管理文件上传接口
     *
     * @param file
     * @param directory
     * @return
     */
    @PostMapping("/upload")
    public R<SysFile> upload(@RequestParam("file") MultipartFile file, @RequestParam("directory") String directory) {
        try {
            // 上传并返回访问地址
            String url = sysFileService.handleFilingUpload(file, directory);
            SysFile sysFile = new SysFile();
            sysFile.setName(FileUtils.getName(file.getOriginalFilename()));
            sysFile.setUrl(url);
            return R.ok(sysFile);
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return R.fail(e.getMessage());
        }
    }

    @PostMapping("ls")
    public R<List<OSSObjectSummary>> listFiles(String prefix) {
        try {
            ListObjectsV2Result result = sysFileService.listFiles(prefix);
            return R.ok(result.getObjectSummaries());
        } catch (Exception e) {
            log.error("获取文件列表失败", e);
            return R.fail(e.getMessage());
        }
    }

    @PostMapping("rm")
    public R<DeleteObjectsResult> deleteFiles(@RequestBody List<String> keys) {
        try {
            return R.ok(sysFileService.deleteFiles(keys));
        } catch (Exception e) {
            log.error("删除文件失败", e);
            return R.fail(e.getMessage());
        }
    }
}