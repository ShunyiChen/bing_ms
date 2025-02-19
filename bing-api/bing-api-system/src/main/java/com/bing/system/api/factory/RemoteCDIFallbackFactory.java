package com.bing.system.api.factory;

import com.bing.system.api.model.*;
import com.bing.common.core.domain.R;
import com.bing.system.api.RemoteCDIService;
import com.bing.system.api.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 * 
 * @author Simeon
 */
@Component
public class RemoteCDIFallbackFactory implements FallbackFactory<RemoteCDIService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteCDIFallbackFactory.class);

    @Override
    public RemoteCDIService create(Throwable throwable)
    {
        log.error("CDI服务调用失败:{}", throwable.getMessage());
        return new RemoteCDIService()
        {
            @Override
            public R<CDIListEmployee> getEmployeeByLPN(LPN lpn, String source) {
                return R.fail("CDI通过LPN获取Employee信息失败:" + throwable.getMessage());
            }

            @Override
            public R<CDIOneEmployee> getOneEmployeeByEmail(Email email, String source) {
                return R.fail("CDI通过Email获取Employee信息失败:" + throwable.getMessage());
            }

            @Override
            public R<CDIEngagement> getEngagementByEID(EID eid, String source) {
                return R.fail("CDI获取Engagement信息失败:" + throwable.getMessage());
            }
        };
    }
}
