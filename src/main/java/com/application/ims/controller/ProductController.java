package com.application.ims.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.application.ims.domain.service.interfaces.ProductServiceInterface;
import com.application.ims.domain.dto.response.ProductResponseDto;
import com.application.ims.domain.dto.request.create.ProductRequestDto;
import com.application.ims.domain.dto.request.update.UpdateProductRequestDto;
import com.application.ims.domain.dto.request.update.AddProductStockRequestDto;

import java.util.List;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/product/")
public class ProductController {

    private final ProductServiceInterface productService;

    @Autowired
    public ProductController(ProductServiceInterface productService) {
        this.productService = productService;
    }

    // POST method
    @PostMapping("/add/") // http://localhost:8080/api/product/add/
    public ProductResponseDto save(@RequestBody ProductRequestDto productRequestDto) {
        return productService.save(productRequestDto);
    }

    // GET method (product per id)
    @GetMapping("{id}") // http://localhost:8080/api/product/{id}/
    public ProductResponseDto getProduct(
            @PathVariable Long id
    ) {
        return productService.getProduct(id);
    }

    // GET method (all products)
    @GetMapping // http://localhost:8080/api/product/
    public List<ProductResponseDto> getProducts() {
        return productService.getProducts();
    }

    // PUT method
    @PutMapping("/{id}/update/") // http://localhost:8080/api/product/{id}/update/
    public ProductResponseDto update(
            @PathVariable Long id,
            @RequestBody UpdateProductRequestDto productRequestDto
    ) {
        return productService.update(id, productRequestDto);
    }

    // PATCH method
    @PatchMapping("/{id}/update-stock/") // http://localhost:8080/api/product/{id}/update-stock/
    public ProductResponseDto updateStock(
            @PathVariable Long id,
            @RequestBody AddProductStockRequestDto productRequestDto
    ) {
        return productService.updateStock(id, productRequestDto);
    }

    // DELETE method
    @DeleteMapping("/{id}/delete/") // http://localhost:8080/api/product/{id}/delete/
    public ProductResponseDto delete(@PathVariable Long id) {
        return productService.delete(id);
    }
}
