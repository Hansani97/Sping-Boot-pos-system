package com.devstack.posapp.util.mapper;

import com.devstack.posapp.dto.core.CustomerDto;
import com.devstack.posapp.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CustomerDto dto);
}
