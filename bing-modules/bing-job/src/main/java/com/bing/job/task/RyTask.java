package com.bing.job.task;

import com.bing.system.api.RemoteFilingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bing.common.core.utils.StringUtils;

/**
 * 定时任务调度测试
 * 
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask
{
    @Autowired
    private RemoteFilingService remoteFilingService;

    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams()
    {
        System.out.println("执行无参方法");
    }

    public void over6MonthsOutstandingFiles()
    {
        remoteFilingService.over6MonthsOutstandingFiles();
    }

    public void over1MonthsOutstandingBoxes()
    {
        remoteFilingService.over1MonthsOutstandingBoxes();
    }
}
