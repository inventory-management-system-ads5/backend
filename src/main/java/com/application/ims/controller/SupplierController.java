package com.application.ims.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.application.ims.domain.service.interfaces.SupplierServiceInterface;
import com.application.ims.domain.dto.response.SupplierResponseDto;
import com.application.ims.domain.dto.request.create.SupplierRequestDto;
import com.application.ims.domain.dto.request.update.UpdateSupplierRequestDto;
import com.application.ims.domain.dto.request.update.UpdateSupplierStatusRequestDto;

import java.util.List;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/supplier/")
public class SupplierController {

    private final SupplierServiceInterface supplierService;

    @Autowired
    public SupplierController(SupplierServiceInterface supplierService) {
        this.supplierService = supplierService;

    }

    // POST method
    @PostMapping("/add/") // http://localhost:8080/api/supplier/add/
    public SupplierResponseDto save(@RequestBody SupplierRequestDto supplierRequestDto) {
        return supplierService.save(supplierRequestDto);
    }

    // GET method (supplier per id)
    @GetMapping("/{id}/") // http://localhost:8080/api/supplier/{id}/
    public SupplierResponseDto getSupplier(
            @PathVariable Long id
    ) {
        return supplierService.getSupplier(id);
    }

    // GET method (all suppliers)
    @GetMapping // http://localhost:8080/api/supplier/
    public List<SupplierResponseDto> getSuppliers() {
        return supplierService.getSuppliers();
    }

    // PUT method
    @PutMapping("/{id}/update/") // http://localhost:8080/api/supplier/{id}/update/
    public SupplierResponseDto update(
            @PathVariable Long id,
            @RequestBody UpdateSupplierRequestDto supplierRequestDto
    ) {
        return supplierService.update(id, supplierRequestDto);
    }

    // PATCH method
    @PatchMapping("{id}/update-status/") // http://localhost:8080/api/supplier/{id}/update-status/
    public SupplierResponseDto updateStatus(
            @PathVariable Long id,
            @RequestBody UpdateSupplierStatusRequestDto supplierRequestDto
    ) {
        return supplierService.updateStatus(id, supplierRequestDto);
    }

    // DELETE method
    @DeleteMapping("/{id}/delete/") // http://localhost:8080/api/supplier/{id}/delete/
    public SupplierResponseDto delete(
            @PathVariable Long id
    ) {
        return supplierService.delete(id);
    }

}
