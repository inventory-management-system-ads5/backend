package com.application.ims.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.application.ims.domain.service.interfaces.CustomerServiceInterface;
import com.application.ims.domain.dto.response.CustomerResponseDto;
import com.application.ims.domain.dto.request.create.CustomerRequestDto;
import com.application.ims.domain.dto.request.update.UpdateCustomerRequestDto;
import com.application.ims.domain.dto.request.update.UpdateCustomerStatusRequestDto;

import java.util.List;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/customer/")
public class CustomerController {

    private final CustomerServiceInterface customerService;

    @Autowired
    public CustomerController(CustomerServiceInterface customerService) {
        this.customerService = customerService;

    }

    // POST method
    @PostMapping("/add/") // http://localhost:8080/api/customer/add/
    public CustomerResponseDto save(@RequestBody CustomerRequestDto customerRequestDto) {
        return customerService.save(customerRequestDto);

    }

    // GET method (customer per id)
    @GetMapping("/{id}/") // http://localhost:8080/api/customer/{id}/
    public CustomerResponseDto getCustomer(
            @PathVariable Long id
    ) {
        return customerService.getCustomer(id);
    }

    // GET method (all customers)
    @GetMapping // http://localhost:8080/api/customer/
    public List<CustomerResponseDto> getCustomers() {
        return customerService.getCustomers();
    }

    // PUT method
    @PutMapping("/{id}/update/") // http://localhost:8080/api/customer/{id}/update/
    public CustomerResponseDto update(
            @PathVariable Long id,
            @RequestBody UpdateCustomerRequestDto customerRequestDto
    ) {
        return customerService.update(id, customerRequestDto);
    }

    // PATCH method
    @PatchMapping("{id}/update-status/") // http://localhost:8080/api/customer/{id}/update-status/
    public CustomerResponseDto updateStatus(
            @PathVariable Long id,
            @RequestBody UpdateCustomerStatusRequestDto customerRequestDto
    ) {
        return customerService.updateStatus(id, customerRequestDto);
    }

    // DELETE method
    @DeleteMapping(" {id}/delete/") // http://localhost:8080/api/customer/{id}/delete/
    public CustomerResponseDto delete(@PathVariable Long id) {
        return customerService.delete(id);
    }

}
