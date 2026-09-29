package com.lsxuan.saas.infrastructure.persistence.customer;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lsxuan.saas.domain.customer.CustomerStatus;
import com.lsxuan.saas.infrastructure.persistence.typehandler.UUIDTypeHandler;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@TableName(value = "customer", autoResultMap = true)
public class CustomerDO {

    @TableField(typeHandler = UUIDTypeHandler.class)
    private UUID id;
    @TableField(typeHandler = UUIDTypeHandler.class)
    private UUID tenantId;
    private String name;
    private String contactName;
    private String contactPhone;
    private String email;
    private CustomerStatus status;
    private LocalDateTime createdAt;
    @TableField(typeHandler = UUIDTypeHandler.class)
    private UUID createdBy;
    private LocalDateTime updatedAt;
    @TableField(typeHandler = UUIDTypeHandler.class)
    private UUID updatedBy;
}