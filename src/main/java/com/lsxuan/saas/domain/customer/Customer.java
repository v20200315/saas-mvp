package com.lsxuan.saas.domain.customer;

import com.github.f4b6a3.uuid.UuidCreator;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Customer {

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

    protected Customer() {
    }

    public static Customer create(UUID tenantId, String name, String contactName, String contactPhone, String email,
        UUID operatorId) {
        validateTenantId(tenantId);
        validateName(name);
        validateOperatorId(operatorId);

        Customer customer = new Customer();

        LocalDateTime now = LocalDateTime.now();

        customer.id = UuidCreator.getTimeOrderedEpoch();
        customer.tenantId = tenantId;
        customer.name = name;
        customer.contactName = contactName;
        customer.contactPhone = contactPhone;
        customer.email = email;
        customer.status = CustomerStatus.ACTIVE;
        customer.createdAt = now;
        customer.createdBy = operatorId;
        customer.updatedAt = now;
        customer.updatedBy = operatorId;

        return customer;
    }

    public static Customer reconstitute(UUID id, UUID tenantId, String name, String contactName, String contactPhone,
        String email, CustomerStatus status, LocalDateTime createdAt, UUID createdBy, LocalDateTime updatedAt,
        UUID updatedBy) {

        Customer customer = new Customer();

        customer.id = id;
        customer.tenantId = tenantId;
        customer.name = name;
        customer.contactName = contactName;
        customer.contactPhone = contactPhone;
        customer.email = email;
        customer.status = status;
        customer.createdAt = createdAt;
        customer.createdBy = createdBy;
        customer.updatedAt = updatedAt;
        customer.updatedBy = updatedBy;

        return customer;
    }

    public void updateBasicInfo(String name, String contactName, String contactPhone, String email, UUID operatorId) {
        if (this.status == CustomerStatus.INACTIVE) {
            throw new IllegalStateException("已停用的客户不能修改");
        }

        validateName(name);
        validateOperatorId(operatorId);

        this.name = name;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.email = email;
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = operatorId;
    }

    public void deactivate(UUID operatorId) {

        validateOperatorId(operatorId);

        if (this.status == CustomerStatus.INACTIVE) {
            return;
        }

        this.status = CustomerStatus.INACTIVE;
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = operatorId;
    }

    public void activate(UUID operatorId) {

        validateOperatorId(operatorId);

        if (this.status == CustomerStatus.ACTIVE) {
            return;
        }

        this.status = CustomerStatus.ACTIVE;
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = operatorId;
    }

    private static void validateTenantId(UUID tenantId) {

        if (tenantId == null) {
            throw new IllegalArgumentException("tenantId不能为空");
        }
    }

    private static void validateName(String name) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("客户名称不能为空");
        }
    }

    private static void validateOperatorId(UUID operatorId) {

        if (operatorId == null) {
            throw new IllegalArgumentException("operatorId不能为空");
        }
    }
}