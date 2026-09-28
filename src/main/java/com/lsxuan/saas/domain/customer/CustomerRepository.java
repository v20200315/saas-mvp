package com.lsxuan.saas.domain.customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    
    void save(Customer customer);

    Optional<Customer> findById(UUID tenantId, UUID id);

    List<Customer> findAll(UUID tenantId);

    void update(Customer customer);

    void delete(UUID tenantId, UUID id);
}