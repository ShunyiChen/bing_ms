package com.bing.system.api;

import com.bing.system.api.model.*;
import com.bing.common.core.constant.SecurityConstants;
import com.bing.common.core.constant.ServiceNameConstants;
import com.bing.common.core.domain.R;
import com.bing.system.api.factory.RemoteCDIFallbackFactory;
import com.bing.system.api.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * CDI接口调用服务
 */
@FeignClient(contextId = "remoteCDIService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteCDIFallbackFactory.class)
public interface RemoteCDIService {
    @PostMapping("/cdi/getEmployeeByLPN")
    R<CDIListEmployee> getEmployeeByLPN(@RequestBody LPN lpn, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @PostMapping("/cdi/getOneEmployeeByEmail")
    R<CDIOneEmployee> getOneEmployeeByEmail(@RequestBody Email email, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @PostMapping("/cdi/getEngagementByEID")
    R<CDIEngagement> getEngagementByEID(@RequestBody EID eid, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
