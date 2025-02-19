package com.bing.file.utils;

import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * MultipartFile文件工具类
 *
 * @author Simeon
 */
public class MultipartFileUtils {

    public static String createDirectoryWithDate(String baseDir) {
        LocalDate currentDate = LocalDate.now();
        // 获取年份、月份和日期
        String year = String.valueOf(currentDate.getYear());
        String month = String.format("%02d", currentDate.getMonthValue()); // 格式化为两位数
        String day = String.format("%02d", currentDate.getDayOfMonth()); // 格式化为两位数
        return baseDir + year + "/" + month + "/" + day + "/";
    }

    public static String getFileNameWithTimestamp(MultipartFile file) {
        // 获取原始文件名
        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null) {
            return null; // 或者抛出异常
        }
        // 获取文件扩展名
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        // 获取当前时间戳
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        // 生成新的文件名
        return originalFileName.replace(extension, "") + "_" + timestamp + extension;
    }
}
