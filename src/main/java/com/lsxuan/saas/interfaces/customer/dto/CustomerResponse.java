package com.lsxuan.saas.interfaces.customer.dto;

import com.lsxuan.saas.domain.customer.Customer;
import com.lsxuan.saas.domain.customer.CustomerStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerResponse(UUID id, UUID tenantId, String name, String contactName, String contactPhone,
                               String email, CustomerStatus status, LocalDateTime createdAt, UUID createdBy,
                               LocalDateTime updatedAt, UUID updatedBy) {

    public static CustomerResponse from(Customer customer) {
        
        return new CustomerResponse(customer.getId(), customer.getTenantId(), customer.getName(),
            customer.getContactName(), customer.getContactPhone(), customer.getEmail(), customer.getStatus(),
            customer.getCreatedAt(), customer.getCreatedBy(), customer.getUpdatedAt(), customer.getUpdatedBy());
    }
}