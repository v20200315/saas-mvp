package com.lsxuan.saas.domain.customer;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum CustomerStatus {

    INACTIVE(0), ACTIVE(1);

    @EnumValue
    private final int code;

    CustomerStatus(int code) {
        this.code = code;
    }
}