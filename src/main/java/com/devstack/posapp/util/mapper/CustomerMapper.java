package com.devstack.posapp.util.mapper;

import com.devstack.posapp.dto.core.CustomerDto;
import com.devstack.posapp.dto.response.ResponseCustomerDto;
import com.devstack.posapp.entity.Customer;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CustomerDto dto);
    ResponseCustomerDto toResponseCustomerDto(Customer customer);
    List<ResponseCustomerDto> toResponseCustomerDtoList(Page<Customer> page);
}
