package com.application.ims.domain.service.interfaces;

import com.application.ims.domain.dto.request.create.SupplierRequestDto;
import com.application.ims.domain.dto.request.update.UpdateSupplierRequestDto;
import com.application.ims.domain.dto.request.update.UpdateSupplierStatusRequestDto;
import com.application.ims.domain.dto.response.SupplierResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupplierServiceInterface {
    SupplierResponseDto save(SupplierRequestDto supplierRequestDto);
    SupplierResponseDto getSupplier(Long id);
    List<SupplierResponseDto> getSuppliers();
    SupplierResponseDto update(Long id, UpdateSupplierRequestDto supplierRequestDto);
    SupplierResponseDto updateStatus(Long id, UpdateSupplierStatusRequestDto supplierRequestDto);
    SupplierResponseDto delete(Long id);

}
