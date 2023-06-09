package com.devstack.posapp.service;

import com.devstack.posapp.dto.request.RequestCustomerDto;
import com.devstack.posapp.dto.response.ResponseCustomerDto;
import com.devstack.posapp.dto.response.paginated.model.CustomerPaginatedDto;

public interface CustomerService {
    public ResponseCustomerDto createCustomer(RequestCustomerDto dto);
    public ResponseCustomerDto findCustomer(long id);
    public ResponseCustomerDto updateCustomer(long id, RequestCustomerDto dto);
    public void deleteCustomer(long id);
    public CustomerPaginatedDto findAllCustomer(int page, int size, String searchText);
}
