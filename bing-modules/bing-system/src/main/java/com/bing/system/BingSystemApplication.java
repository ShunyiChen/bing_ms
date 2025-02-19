package com.bing.system;

import com.bing.common.core.annotation.EnableNacosPropertyDecryption;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.bing.common.security.annotation.EnableCustomConfig;
import com.bing.common.security.annotation.EnableRyFeignClients;
//import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 系统模块
 * 
 * @author ruoyi
 */
@EnableCustomConfig
//@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
@EnableScheduling
@EnableNacosPropertyDecryption
public class BingSystemApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(BingSystemApplication.class, args);
        System.out.println("系统模块启动成功\n\n" +
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
