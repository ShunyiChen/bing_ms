package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class ChangeBoxBorrowStatusDTO extends BaseEntity {
    private List<Key> keys;
    private Integer status;
    private String returnChargeCode;

    @Data
    public static class Key {
        private Long id; // borrow id
        private Long deptId;
        private Long boxId;
        private String boxNo;
        private String staffId;
        private String staffName;
        private String staffEmail;
    }
}
