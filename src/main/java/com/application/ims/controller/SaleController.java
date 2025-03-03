package com.application.ims.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.application.ims.domain.service.interfaces.SaleServiceInterface;
import com.application.ims.domain.dto.response.SaleResponseDto;
import com.application.ims.domain.dto.request.create.SaleRequestDto;
import com.application.ims.domain.dto.request.update.UpdateSaleStatusRequestDto;

import java.util.List;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/sale/")
public class SaleController {

    private final SaleServiceInterface saleService;

    @Autowired
    public SaleController(SaleServiceInterface saleService) {
        this.saleService = saleService;
    }

    // POST method
    @PostMapping("/add/") // http://localhost:8080/api/sale/add/
    public SaleResponseDto save(@RequestBody SaleRequestDto saleRequestDto) {
        return saleService.save(saleRequestDto);
    }

    // GET method (sale per id)
    @GetMapping("/{id}/") // http://localhost:8080/api/sale/{id}/
    public SaleResponseDto getSale(
            @PathVariable Long id
    ) {
        return saleService.getSale(id);
    }

    // GET method (all sales)
    @GetMapping // http://localhost:8080/api/sale/
    public List<SaleResponseDto> getSales() {
        return saleService.getSales();
    }

    // PATCH method
    @PatchMapping("{id}/update-status/") // http://localhost:8080/api/sale/{id}/update-status/
    public SaleResponseDto updateStatus(
            @PathVariable Long id
    ) {
        return saleService.updateStatus(id);
    }

}
