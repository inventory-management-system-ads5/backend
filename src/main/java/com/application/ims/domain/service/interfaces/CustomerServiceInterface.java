package com.application.ims.domain.service.interfaces;

import com.application.ims.domain.dto.request.create.CustomerRequestDto;
import com.application.ims.domain.dto.request.update.UpdateCustomerRequestDto;
import com.application.ims.domain.dto.request.update.UpdateCustomerStatusRequestDto;
import com.application.ims.domain.dto.response.CustomerResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerServiceInterface {
    CustomerResponseDto save(CustomerRequestDto customerRequestDto);
    CustomerResponseDto getCustomer(Long id);
    List<CustomerResponseDto> getCustomers();
    CustomerResponseDto update(Long id, UpdateCustomerRequestDto customerRequestDto);
    CustomerResponseDto updateStatus(Long id, UpdateCustomerStatusRequestDto customerRequestDto);
    CustomerResponseDto delete(Long id);

}
