package com.application.ims.domain.service.implementation;

import org.springframework.stereotype.Service;
import com.application.ims.domain.service.interfaces.CustomerServiceInterface;
import com.application.ims.infrastructure.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.application.ims.domain.dto.response.CustomerResponseDto;
import com.application.ims.domain.entity.Customer;
import com.application.ims.domain.dto.request.create.CustomerRequestDto;
import com.application.ims.domain.dto.request.update.UpdateCustomerRequestDto;
import com.application.ims.domain.dto.request.update.UpdateCustomerStatusRequestDto;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerServiceInterface {
    
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    // POST method implementation
    @Override
    public CustomerResponseDto save(CustomerRequestDto customerRequestDto) {

        // creating a new customer
        Customer customer = new Customer();

        // setting up the new customer attributes
        customer.setName(customerRequestDto.getName());
        customer.setEmail(customerRequestDto.getEmail());
        customer.setContactInfo(customerRequestDto.getContact_info());
        customer.setActive(true);

        Customer savedCustomer = customerRepository.save(customer);

        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setId(savedCustomer.getId());
        customerResponseDto.setName(savedCustomer.getName());
        customerResponseDto.setContact_info(savedCustomer.getContactInfo());
        customerResponseDto.setIs_active(savedCustomer.isActive());


        return customerResponseDto;

    }

    // GET method implementation
    @Override
    public CustomerResponseDto getCustomer(Long id) {

        // fetching the customer with the given id
        Customer customer = customerRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Customer with id " + id + " not found"));

        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setId(customer.getId());
        customerResponseDto.setName(customer.getName());
        customerResponseDto.setEmail(customer.getEmail());
        customerResponseDto.setContact_info(customer.getContactInfo());
        customerResponseDto.setIs_active(customer.isActive());

        return customerResponseDto;
    }

    // GET method implementation (list)
    @Override
    public List<CustomerResponseDto> getCustomers() {

        // fetching all existing customers
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(this::customerResponseDtos).toList();

    }

    private CustomerResponseDto customerResponseDtos(Customer customer) {
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setId(customer.getId());
        customerResponseDto.setName(customer.getName());
        customerResponseDto.setEmail(customer.getEmail());
        customerResponseDto.setContact_info(customer.getContactInfo());
        customerResponseDto.setIs_active(customer.isActive());

        return customerResponseDto;
    }

    // PUT method implementation
    @Override
    public CustomerResponseDto update(Long id, UpdateCustomerRequestDto customerRequestDto) {

        // fetching the customer with the given id
        Customer customer = customerRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Customer with id " + id + " not found"));

        customer.setName(customerRequestDto.getName());
        customer.setEmail(customerRequestDto.getEmail());
        customer.setContactInfo(customerRequestDto.getContact_info());

        // updating the customer with new given data
        Customer updatedCustomer = customerRepository.save(customer);

        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setId(updatedCustomer.getId());
        customerResponseDto.setName(customer.getName());
        customerResponseDto.setEmail(customer.getEmail());
        customerResponseDto.setContact_info(customer.getContactInfo());
        customerResponseDto.setIs_active(customer.isActive());

        return customerResponseDto;
    }

    // PATCH method implementation
    @Override
    public CustomerResponseDto updateStatus(Long id, UpdateCustomerStatusRequestDto customerRequestDto) {

        // fetching the customer with the given id
        Customer customer = customerRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Customer with id " + id + " not found"));

        customer.setActive(customerRequestDto.getIs_active());

        // updating the customer status
        Customer updatedCustomer = customerRepository.save(customer);

        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setId(updatedCustomer.getId());
        customerResponseDto.setName(customer.getName());
        customerResponseDto.setEmail(customer.getEmail());
        customerResponseDto.setContact_info(customer.getContactInfo());
        customerResponseDto.setIs_active(customer.isActive());

        return customerResponseDto;
    }

    // DELETE method implementation
    @Override
    public CustomerResponseDto delete(Long id) {

        // fetching the customer with the given id
        Customer customer = customerRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Customer with id " + id + " not found"));

        customerRepository.delete(customer);

        return null;
    }
}
