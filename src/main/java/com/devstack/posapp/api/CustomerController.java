package com.devstack.posapp.api;

import com.devstack.posapp.dto.request.RequestCustomerDto;
import com.devstack.posapp.service.CustomerService;
import com.devstack.posapp.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/customers"
        /*consumes = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
        }*/
)
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping/*("/create")*/ //for save - POST
    public ResponseEntity<StandardResponse> createCustomer(@RequestBody RequestCustomerDto customerDto) {
        return new ResponseEntity<>(
                new StandardResponse(201, "Saved Customer", customerService.createCustomer(customerDto)),
                HttpStatus.CREATED
        );
    }

    @PutMapping(params = "id")/*("/update")*/ // for update - PUT
    public ResponseEntity<StandardResponse> updateCustomer(@RequestParam long id, @RequestBody RequestCustomerDto customerDto) throws ClassNotFoundException {
        return new ResponseEntity<>(
                new StandardResponse(201, "Customer Updated", customerService.updateCustomer(id, customerDto)),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")/*("/delete")*/ //for delete DELETE
    public ResponseEntity<StandardResponse> deleteCustomer(@PathVariable long id) throws ClassNotFoundException {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(
                new StandardResponse(204, "Customer Deleted", null)
                , HttpStatus.NO_CONTENT
        );
    }

    @GetMapping("/{id}")/*("/find") *///for get,find - GET
    public ResponseEntity<StandardResponse> findCustomer(@PathVariable long id) throws ClassNotFoundException {
        return new ResponseEntity<>(
                new StandardResponse(200, "Customer Detail", customerService.findCustomer(id))
                , HttpStatus.OK
        );
    }

    @GetMapping(value = "list", params = {"page", "size", "searchText"})
    public ResponseEntity<StandardResponse> getAllCustomers(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String searchText
    ) {
        return new ResponseEntity<>(
                new StandardResponse(200, "Customer Detail", customerService.findAllCustomer(page, size, searchText))
                , HttpStatus.OK
        );
    }
}
