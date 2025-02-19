package com.bing.modules.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 监控中心
 * 
 * @author ruoyi
 */
@EnableAdminServer
@SpringBootApplication
public class BingMonitorApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(BingMonitorApplication.class, args);
        System.out.println("监控中心启动成功\n\n" +
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
