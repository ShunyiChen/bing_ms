package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import com.ruoyi.filingsystem.domain.FilingAttachment;
import lombok.Data;

import java.util.List;

@Data
public class SaveAttachmentsDTO extends BaseEntity {
    private List<Key> keys;
    private FilingAttachment[] attachments;

    @Data
    public static class Key {
        private Long deptId;
        private Long id; // box id
    }
}
