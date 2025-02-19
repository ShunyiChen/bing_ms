package com.ruoyi.filingsystem.dto;

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class ChangeChargeCodeDTO extends BaseEntity {
    private List<Key> keys;
    private String chargeCode;
    private String returnChargeCode;

    @Data
    public static class Key {
        private Long id; // box profile id 或者 box borrow id
        private Long deptId;
    }
}
