package com.bing.bing.dto;

import com.bing.common.core.web.domain.BaseEntity;
import com.bing.common.core.web.page.TableDataInfo;
import lombok.Data;

@Data
public class LegalDpnRequestListDTO extends BaseEntity {
    private LegalDpnRequestCountDTO count;
    private TableDataInfo table;
}
