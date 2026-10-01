package com.lsxuan.saas.application.customer;

import com.lsxuan.saas.domain.customer.Customer;
import com.lsxuan.saas.domain.customer.CustomerRepository;
import com.lsxuan.saas.infrastructure.security.CurrentUserContext;
import com.lsxuan.saas.interfaces.api.v1.customer.dto.CreateCustomerRequest;
import com.lsxuan.saas.interfaces.api.v1.customer.dto.CustomerResponse;
import com.lsxuan.saas.interfaces.api.v1.customer.dto.UpdateCustomerRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerApplicationService {

    private final CustomerRepository customerRepository;
    private final CurrentUserContext currentUserContext;

    public CustomerApplicationService(CustomerRepository customerRepository, CurrentUserContext currentUserContext) {

        this.customerRepository = customerRepository;
        this.currentUserContext = currentUserContext;
    }

    @Transactional
    public CustomerResponse create(CreateCustomerRequest request) {

        UUID tenantId = currentUserContext.tenantId();
        UUID userId = currentUserContext.userId();

        Customer customer =
            Customer.create(tenantId, request.name(), request.contactName(), request.contactPhone(), request.email(),
                userId);
        customerRepository.save(customer);

        return CustomerResponse.from(customer);
    }

    @Transactional(readOnly = true)
    public CustomerResponse findById(UUID id) {

        UUID tenantId = currentUserContext.tenantId();

        Customer customer =
            customerRepository.findById(tenantId, id).orElseThrow(() -> new IllegalArgumentException("客户不存在"));

        return CustomerResponse.from(customer);
    }

    @Transactional(readOnly = true)
    public List<CustomerResponse> findAll() {

        UUID tenantId = currentUserContext.tenantId();

        return customerRepository.findAll(tenantId).stream().map(CustomerResponse::from).toList();
    }

    @Transactional
    public CustomerResponse update(UUID id, UpdateCustomerRequest request) {

        UUID tenantId = currentUserContext.tenantId();
        UUID userId = currentUserContext.userId();

        Customer customer =
            customerRepository.findById(tenantId, id).orElseThrow(() -> new IllegalArgumentException("客户不存在"));
        customer.updateBasicInfo(request.name(), request.contactName(), request.contactPhone(), request.email(),
            userId);
        customerRepository.update(customer);

        return CustomerResponse.from(customer);
    }

    @Transactional
    public void delete(UUID id) {
        
        UUID tenantId = currentUserContext.tenantId();

        Customer customer =
            customerRepository.findById(tenantId, id).orElseThrow(() -> new IllegalArgumentException("客户不存在"));
        customer.deactivate(currentUserContext.userId());

        customerRepository.update(customer);
    }
}