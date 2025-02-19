package com.bing.system.api.factory;

import com.bing.common.core.domain.R;
import com.bing.system.api.RemoteFilingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Filing远程服务降级处理
 * 
 * @author Simeon
 */
@Component
public class RemoteFilingFallbackFactory implements FallbackFactory<RemoteFilingService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteFilingFallbackFactory.class);

    @Override
    public RemoteFilingService create(Throwable throwable)
    {
        log.error("Filing远程服务调用失败:{}", throwable.getMessage());
        return new RemoteFilingService()
        {
            @Override
            public R<Void> over6MonthsOutstandingFiles()
            {
                return R.fail("6个月未还邮件发送失败:" + throwable.getMessage());
            }

            @Override
            public R<Void> over1MonthsOutstandingBoxes() {
                return R.fail("1个月未还邮件发送失败:" + throwable.getMessage());
            }
        };
    }
}
