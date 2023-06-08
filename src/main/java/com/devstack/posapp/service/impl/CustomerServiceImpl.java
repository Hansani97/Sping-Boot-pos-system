package com.devstack.posapp.service.impl;

import com.devstack.posapp.dto.core.CustomerDto;
import com.devstack.posapp.dto.request.RequestCustomerDto;
import com.devstack.posapp.dto.response.ResponseCustomerDto;
import com.devstack.posapp.dto.response.paginated.model.CustomerPaginatedDto;
import com.devstack.posapp.entity.Customer;
import com.devstack.posapp.repo.CustomerRepo;
import com.devstack.posapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    
    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }


    @Override
    public ResponseCustomerDto createCustomer(RequestCustomerDto dto) {
        CustomerDto customerDto = new CustomerDto(
                0,
                new Random().nextInt(100001),
                dto.getName(),
                dto.getAddress(),
                dto.getSalary(),
                true,
                null,
                null,
                null,
                null
        );
        Customer customer = new Customer(
                0,
                new Random().nextInt(100001),
                customerDto.getName(),
                customerDto.getAddress(),
                customerDto.getSalary(),
                customerDto.isActiveState(),
                null
        );
        customerRepo.save(customer);
        return new ResponseCustomerDto(
                customerDto.getPublicId(),
                customerDto.getName(),
                customerDto.getAddress(),
                customerDto.getSalary(),
                customerDto.isActiveState()
        );
    }

    @Override
    public ResponseCustomerDto findCustomer(long id) throws ClassNotFoundException {
        Optional<Customer> selectedCustomer = customerRepo.findByPublicId(id);
        if (selectedCustomer.isPresent()){
            return new ResponseCustomerDto(
                    selectedCustomer.get().getPublicId(),
                    selectedCustomer.get().getName(),
                    selectedCustomer.get().getAddress(),
                    selectedCustomer.get().getSalary(),
                    selectedCustomer.get().isActiveState()
            );
        }
        throw new ClassNotFoundException();
    }

    @Override
    public ResponseCustomerDto updateCustomer(long id, RequestCustomerDto dto) throws ClassNotFoundException {
        Optional<Customer> selectedCustomer = customerRepo.findByPublicId(id);
        if (selectedCustomer.isPresent()){
            selectedCustomer.get().setName(dto.getName());
            selectedCustomer.get().setAddress(dto.getAddress());
            selectedCustomer.get().setSalary(dto.getSalary());

            customerRepo.save(selectedCustomer.get());
            return new ResponseCustomerDto(
                    selectedCustomer.get().getPublicId(),
                    selectedCustomer.get().getName(),
                    selectedCustomer.get().getAddress(),
                    selectedCustomer.get().getSalary(),
                    selectedCustomer.get().isActiveState()
            );
        }
        throw new ClassNotFoundException();
    }

    @Override
    public void deleteCustomer(long id) {

    }

    @Override
    public CustomerPaginatedDto findAllCustomer(int page, int size, String searchText) {
        return null;
    }
}
