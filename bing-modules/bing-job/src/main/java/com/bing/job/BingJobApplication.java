package com.bing.job;

import com.bing.common.core.annotation.EnableNacosPropertyDecryption;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.bing.common.security.annotation.EnableCustomConfig;
import com.bing.common.security.annotation.EnableRyFeignClients;
//import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 定时任务
 * 
 * @author ruoyi
 */
@EnableCustomConfig
//@EnableCustomSwagger2
@EnableRyFeignClients   
@SpringBootApplication
@EnableNacosPropertyDecryption
public class BingJobApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(BingJobApplication.class, args);
        System.out.println("定时任务模块启动成功\n\n" +
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
