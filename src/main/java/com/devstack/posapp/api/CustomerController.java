package com.devstack.posapp.api;

import com.devstack.posapp.db.Database;
import com.devstack.posapp.dto.request.RequestCustomerDto;
import com.devstack.posapp.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    @PostMapping/*("/create")*/ //for save - POST
    public ResponseEntity<StandardResponse> createCustomer(@RequestBody RequestCustomerDto customerDto) {
        var savedData = Database.createCustomer(customerDto);
        return new ResponseEntity<>(
                new StandardResponse(201, "Saved Customer", savedData),
                HttpStatus.CREATED
        );
    }

    @PutMapping(params = "id")/*("/update")*/ // for update - PUT
    public ResponseEntity<StandardResponse> updateCustomer(@RequestParam long id, @RequestBody RequestCustomerDto customerDto) throws ClassNotFoundException {
        var savedData = Database.updateCustomer(id, customerDto);
        return new ResponseEntity<>(
                new StandardResponse(201, "Customer Updated", savedData),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")/*("/delete")*/ //for delete DELETE
    public ResponseEntity<StandardResponse> deleteCustomer(@PathVariable long id) throws ClassNotFoundException {
        Database.deleteCustomer(id);
        return new ResponseEntity<>(
                new StandardResponse(204, "Customer Deleted", null)
                , HttpStatus.NO_CONTENT
        );
    }

    @GetMapping("/{id}")/*("/find") *///for get,find - GET
    public ResponseEntity<StandardResponse> findCustomer(@PathVariable long id) throws ClassNotFoundException {
        return new ResponseEntity<>(
                new StandardResponse(200, "Customer Detail", Database.findCustomer(id))
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
                new StandardResponse(200, "Customer Detail", Database.findAllCustomer(page, size, searchText))
                , HttpStatus.OK
        );
    }
}
