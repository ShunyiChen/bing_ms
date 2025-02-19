package com.bing.system.api;

import com.bing.common.core.constant.ServiceNameConstants;
import com.bing.common.core.domain.R;
import com.bing.system.api.factory.RemoteFilingFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Filing远程调用服务
 * 
 * @author Simeon
 */
@FeignClient(contextId = "remoteFilingService", value = ServiceNameConstants.FILING_SYSTEM_SERVICE, fallbackFactory = RemoteFilingFallbackFactory.class)
public interface RemoteFilingService
{
    /**
     * 发邮件通知逾期6个月未还文件的借阅记录人
     *
     * @return
     */
    @PostMapping(value = "/filing/profile/reminder/over6MonthsOutstandingFiles")
    R<Void> over6MonthsOutstandingFiles();

    /**
     * 发邮件通知逾期1个月未还的借阅记录人
     *
     * @return
     */
    @PostMapping(value = "/filing/boxprofile/borrowrecord/reminder/over1MonthsOutstandingBoxes")
    R<Void> over1MonthsOutstandingBoxes();
}
