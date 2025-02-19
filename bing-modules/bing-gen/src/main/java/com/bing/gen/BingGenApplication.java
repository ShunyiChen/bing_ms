package com.bing.gen;

import com.bing.common.core.annotation.EnableNacosPropertyDecryption;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.bing.common.security.annotation.EnableCustomConfig;
import com.bing.common.security.annotation.EnableRyFeignClients;
//import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 代码生成
 * 
 * @author ruoyi
 */
@EnableCustomConfig
//@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
@EnableNacosPropertyDecryption
public class BingGenApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(BingGenApplication.class, args);
        System.out.println("代码生成模块启动成功\n\n" +
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
