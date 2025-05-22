package com.application.ims.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.application.ims.domain.service.interfaces.SupplierServiceInterface;
import com.application.ims.domain.dto.response.SupplierResponseDto;
import com.application.ims.domain.dto.request.create.SupplierRequestDto;
import com.application.ims.domain.dto.request.update.UpdateSupplierRequestDto;
import com.application.ims.domain.dto.request.update.UpdateSupplierStatusRequestDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    // GET method (export all suppliers to csv file )
    @GetMapping("/export/csv/")
    public ResponseEntity<String> exportSuppliersToCsv() {

        List<SupplierResponseDto> suppliers = supplierService.getSuppliers();

        StringWriter writer = new StringWriter();
        // CSV header
        writer.append("ID,Name,Contact Info,Status\n");

        // CSV data
        for (SupplierResponseDto supplier : suppliers) {
            writer.append(String.format("%d,%s,%s,%s\n",
                    supplier.getId(),
                    supplier.getName(),
                    supplier.getContact_info(),
                    supplier.getIs_active() ? "Active" : "Inactive"
            ));
        }

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = String.format("suppliers_export_%s.csv", timestamp);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.add(HttpHeaders.CONTENT_TYPE, "text/csv");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(writer.toString());
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
