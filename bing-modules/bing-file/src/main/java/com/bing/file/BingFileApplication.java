package com.bing.file;

import com.bing.common.core.annotation.EnableNacosPropertyDecryption;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 文件服务
 * 
 * @author Simeon
 */
//@EnableCustomSwagger2
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableNacosPropertyDecryption
public class BingFileApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(BingFileApplication.class, args);
        System.out.println("文件服务模块启动成功\n\n" +
                "  /$$$$$$                                                            \n" +
                " /$$__  $$                                                           \n" +
                "| $$  \\__/ /$$   /$$  /$$$$$$$  /$$$$$$$  /$$$$$$   /$$$$$$$ /$$$$$$$\n" +
                "|  $$$$$$ | $$  | $$ /$$_____/ /$$_____/ /$$__  $$ /$$_____//$$_____/\n" +
                " \\____  $$| $$  | $$| $$      | $$      | $$$$$$$$|  $$$$$$|  $$$$$$ \n" +
                " /$$  \\ $$| $$  | $$| $$      | $$      | $$_____/ \\____  $$\\____  $$\n" +
                "|  $$$$$$/|  $$$$$$/|  $$$$$$$|  $$$$$$$|  $$$$$$$ /$$$$$$$//$$$$$$$/\n" +
                " \\______/  \\______/  \\_______/ \\_______/ \\_______/|_______/|_______/ ");
    }
}
