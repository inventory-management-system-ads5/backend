package com.application.ims.domain.service.implementation;

import org.springframework.stereotype.Service;
import com.application.ims.domain.service.interfaces.SupplierServiceInterface;
import com.application.ims.infrastructure.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.application.ims.domain.dto.response.SupplierResponseDto;
import com.application.ims.domain.entity.Supplier;
import com.application.ims.domain.dto.request.create.SupplierRequestDto;
import com.application.ims.domain.dto.request.update.UpdateSupplierRequestDto;
import com.application.ims.domain.dto.request.update.UpdateSupplierStatusRequestDto;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierServiceInterface {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    // POST method implementation
    @Override
    public SupplierResponseDto save(SupplierRequestDto supplierRequestDto) {

        // creating a new supplier
        Supplier supplier = new Supplier();

        // setting up the new supplier attributes
        supplier.setName(supplierRequestDto.getName());
        supplier.setContactInfo(supplierRequestDto.getContact_info());
        supplier.setActive(true);

        Supplier savedSupplier = supplierRepository.save(supplier);

        SupplierResponseDto supplierResponseDto = new SupplierResponseDto();
        supplierResponseDto.setId(savedSupplier.getId());
        supplierResponseDto.setName(savedSupplier.getName());
        supplierResponseDto.setContact_info(savedSupplier.getContactInfo());
        supplierResponseDto.setIs_active(savedSupplier.isActive());

        return supplierResponseDto;
    }

    // GET method implementation
    @Override
    public SupplierResponseDto getSupplier(Long id) {

        // fetching the supplier with the given id
        Supplier supplier = supplierRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Supplier with id " + id + " not found"));

        SupplierResponseDto supplierResponseDto = new SupplierResponseDto();
        supplierResponseDto.setId(supplier.getId());
        supplierResponseDto.setName(supplier.getName());
        supplierResponseDto.setContact_info(supplier.getContactInfo());
        supplierResponseDto.setIs_active(supplier.isActive());

        return supplierResponseDto;
    }

    // GET method implementation (list)
    @Override
    public List<SupplierResponseDto> getSuppliers() {

        // fetching all existing suppliers
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers.stream().map(this::supplierResponseDtos).toList();
    }

    private SupplierResponseDto supplierResponseDtos(Supplier supplier) {
        SupplierResponseDto supplierResponseDto = new SupplierResponseDto();
        supplierResponseDto.setId(supplier.getId());
        supplierResponseDto.setName(supplier.getName());
        supplierResponseDto.setContact_info(supplier.getContactInfo());
        supplierResponseDto.setIs_active(supplier.isActive());

        return supplierResponseDto;
    }

    // PUT method implementation
    @Override
    public SupplierResponseDto update(Long id, UpdateSupplierRequestDto supplierRequestDto) {

        // fetching the supplier with the given id
        Supplier supplier = supplierRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Supplier with id " + id + " not found"));

        supplier.setName(supplierRequestDto.getName());
        supplier.setContactInfo(supplierRequestDto.getContact_info());

        // updating the supplier with new given data
        Supplier updatedSupplier = supplierRepository.save(supplier);

        SupplierResponseDto supplierResponseDto = new SupplierResponseDto();
        supplierResponseDto.setId(updatedSupplier.getId());
        supplierResponseDto.setName(supplier.getName());
        supplierResponseDto.setContact_info(supplier.getContactInfo());
        supplierResponseDto.setIs_active(supplier.isActive());

        return supplierResponseDto;
    }

    // PATCH method implementation
    @Override
    public SupplierResponseDto updateStatus(Long id, UpdateSupplierStatusRequestDto supplierRequestDto) {

        // fetching the supplier with the given id
        Supplier supplier = supplierRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Supplier with id " + id + " not found"));

        supplier.setActive(supplierRequestDto.getIs_active());

        // updating the supplier status
        Supplier updatedSupplier = supplierRepository.save(supplier);

        SupplierResponseDto supplierResponseDto = new SupplierResponseDto();
        supplierResponseDto.setId(updatedSupplier.getId());
        supplierResponseDto.setName(supplier.getName());
        supplierResponseDto.setContact_info(supplier.getContactInfo());
        supplierResponseDto.setIs_active(supplier.isActive());

        return supplierResponseDto;
    }

    // DELETE method implementation
    @Override
    public SupplierResponseDto delete(Long id) {

        // fetching the supplier with the given id
        Supplier supplier = supplierRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Supplier with id " + id + " not found"));

        supplierRepository.delete(supplier);

        return null;
    }

}
