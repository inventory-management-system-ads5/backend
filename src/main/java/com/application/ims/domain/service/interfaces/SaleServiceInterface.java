package com.application.ims.domain.service.interfaces;

import com.application.ims.domain.dto.request.create.SaleRequestDto;
import com.application.ims.domain.dto.response.SaleResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SaleServiceInterface {
    SaleResponseDto save(SaleRequestDto saleRequestDto);
    SaleResponseDto getSale(Long id);
    List<SaleResponseDto> getSales();
    SaleResponseDto updateStatus(Long id);

}
