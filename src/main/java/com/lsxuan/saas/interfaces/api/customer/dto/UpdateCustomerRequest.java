package com.lsxuan.saas.interfaces.api.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateCustomerRequest(@NotBlank String name, String contactName, String contactPhone,
                                    @Email String email) {
}