package com.lsxuan.saas.interfaces.customer;

import com.lsxuan.saas.application.customer.CustomerApplicationService;
import com.lsxuan.saas.interfaces.customer.dto.CreateCustomerRequest;
import com.lsxuan.saas.interfaces.customer.dto.CustomerResponse;
import com.lsxuan.saas.interfaces.customer.dto.UpdateCustomerRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerApplicationService customerApplicationService;

    public CustomerController(CustomerApplicationService customerApplicationService) {

        this.customerApplicationService = customerApplicationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse create(@Valid @RequestBody CreateCustomerRequest request) {

        return customerApplicationService.create(request);
    }

    @GetMapping("/{id}")
    public CustomerResponse findById(@PathVariable UUID id) {

        return customerApplicationService.findById(id);
    }

    @GetMapping
    public List<CustomerResponse> findAll() {

        return customerApplicationService.findAll();
    }

    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateCustomerRequest request) {

        return customerApplicationService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {

        customerApplicationService.delete(id);
    }
}