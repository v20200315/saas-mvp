package com.lsxuan.saas.infrastructure.persistence.customer;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lsxuan.saas.domain.customer.CustomerStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@TableName("customer")
public class CustomerDO {
    
    private UUID id;
    private UUID tenantId;
    private String name;
    private String contactName;
    private String contactPhone;
    private String email;
    private CustomerStatus status;
    private LocalDateTime createdAt;
    private UUID createdBy;
    private LocalDateTime updatedAt;
    private UUID updatedBy;
}