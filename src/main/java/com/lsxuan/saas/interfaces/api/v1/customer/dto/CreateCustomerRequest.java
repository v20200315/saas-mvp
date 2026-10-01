package com.lsxuan.saas.interfaces.api.v1.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateCustomerRequest(@NotBlank String name, String contactName, String contactPhone,
                                    @Email String email) {
}