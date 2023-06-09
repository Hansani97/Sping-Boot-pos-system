package com.devstack.posapp.service.impl;

import com.devstack.posapp.dto.core.CustomerDto;
import com.devstack.posapp.dto.request.RequestCustomerDto;
import com.devstack.posapp.dto.response.ResponseCustomerDto;
import com.devstack.posapp.dto.response.paginated.model.CustomerPaginatedDto;
import com.devstack.posapp.entity.Customer;
import com.devstack.posapp.repo.CustomerRepo;
import com.devstack.posapp.service.CustomerService;
import com.devstack.posapp.util.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepo customerRepo, CustomerMapper customerMapper) {
        this.customerRepo = customerRepo;
        this.customerMapper = customerMapper;
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
        /*Customer customer = new Customer(
                0,
                new Random().nextInt(100001),
                customerDto.getName(),
                customerDto.getAddress(),
                customerDto.getSalary(),
                customerDto.isActiveState(),
                null
        );*/
        customerRepo.save(customerMapper.toCustomer(customerDto));
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

        customerRepo.deleteByPublicId(id);
    }

    @Override
    public CustomerPaginatedDto findAllCustomer(int page, int size, String searchText) {
        /*Page<Customer> customers = customerRepo.findAll(PageRequest.of(page, size));*/
        Page<Customer> customers = customerRepo.searchAllByAddressOrName(searchText,PageRequest.of(page, size));
        List<ResponseCustomerDto> list = new ArrayList<>();
        for (Customer customer : customers
        ) {
            list.add(new ResponseCustomerDto(
                    customer.getPublicId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getSalary(),
                    customer.isActiveState()
            ));
        }
        return new CustomerPaginatedDto(customerRepo.countDataWithSearchText(searchText), list);
    }
}
