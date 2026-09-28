package com.lsxuan.saas.infrastructure.persistence.customer;

import com.lsxuan.saas.domain.customer.Customer;
import com.lsxuan.saas.domain.customer.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerMapper mapper;

    public CustomerRepositoryImpl(CustomerMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void save(Customer customer) {

        mapper.insert(toDO(customer));
    }

    @Override
    public Optional<Customer> findById(UUID tenantId, UUID id) {

        CustomerDO data = mapper.selectById(id);

        if (data == null) {
            return Optional.empty();
        }

        if (!tenantId.equals(data.getTenantId())) {
            return Optional.empty();
        }

        return Optional.of(toDomain(data));
    }

    @Override
    public List<Customer> findAll(UUID tenantId) {

        return mapper.selectList(new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<CustomerDO>().eq(
            CustomerDO::getTenantId, tenantId)).stream().map(this::toDomain).toList();
    }

    @Override
    public void update(Customer customer) {

        mapper.updateById(toDO(customer));
    }

    @Override
    public void delete(UUID tenantId, UUID id) {

        mapper.delete(
            new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<CustomerDO>().eq(CustomerDO::getId,
                id).eq(CustomerDO::getTenantId, tenantId));
    }

    private CustomerDO toDO(Customer customer) {

        CustomerDO data = new CustomerDO();

        data.setId(customer.getId());
        data.setTenantId(customer.getTenantId());
        data.setName(customer.getName());
        data.setContactName(customer.getContactName());
        data.setContactPhone(customer.getContactPhone());
        data.setEmail(customer.getEmail());
        data.setStatus(customer.getStatus());
        data.setCreatedAt(customer.getCreatedAt());
        data.setCreatedBy(customer.getCreatedBy());
        data.setUpdatedAt(customer.getUpdatedAt());
        data.setUpdatedBy(customer.getUpdatedBy());

        return data;
    }

    private Customer toDomain(CustomerDO data) {
        
        return Customer.reconstitute(data.getId(), data.getTenantId(), data.getName(), data.getContactName(),
            data.getContactPhone(), data.getEmail(), data.getStatus(), data.getCreatedAt(), data.getCreatedBy(),
            data.getUpdatedAt(), data.getUpdatedBy());
    }
}